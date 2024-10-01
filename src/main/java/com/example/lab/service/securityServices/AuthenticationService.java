package com.example.lab.service.securityServices;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.lab.common.message.CommonMessage;
import com.example.lab.common.message.ErrorMessage;
import com.example.lab.dto.UserDto;
import com.example.lab.dto.request.AuthenticationRequest;
import com.example.lab.dto.request.UserRegisterRequest;
import com.example.lab.dto.response.AuthenticationResponse;
import com.example.lab.model.Token;
import com.example.lab.model.User;
import com.example.lab.model.UserInfo;
import com.example.lab.repository.UserInfoRepository;
import com.example.lab.repository.user.TokenRepository;
import com.example.lab.repository.user.UserRepository;
import com.example.lab.service.mail.EmailServiceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	private final UserInfoRepository userInfoRepository;
	private final TokenRepository tokenRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final EmailServiceImpl emailServiceImpl ;
	
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public void register(UserRegisterRequest request, String uri) throws Throwable {
		// check duplicate
		Optional<User> user1 = userRepository.findByEmail(request.getEmail());
		if (!user1.isEmpty())
			throw new Exception(ErrorMessage.AUTH_DUPLICATED_EMAIL_REGISTER);
		Optional<User> user2 = userRepository.findByUserName(request.getUsername());
		if (!user2.isEmpty())
			throw new Exception(ErrorMessage.AUTH_DUPLICATED_USERNAME_REGISTER);
		try {// save user
			User newUser = new User();
			newUser.setUserName(request.getUsername());
			newUser.setPassword(passwordEncoder.encode(request.getPassword()));
			newUser.setRole("ROLE_USER");// default
			newUser.setEmail(request.getEmail());
			newUser.setIsActive(false);
			User createdUser = userRepository.save(newUser);
			// save info
			UserInfo userInfo = new UserInfo();
			userInfo.setAddress(request.getAddress());
			userInfo.setFirstName(request.getFirstName());
			userInfo.setLastName(request.getLastName());
			userInfo.setUser(createdUser);
			userInfoRepository.save(userInfo);

			String jwtToken = jwtService.generateActiveToken(createdUser);
			Token token = new Token(jwtToken, "Bearer", false, false, createdUser.getId());
			tokenRepository.save(token);

			emailServiceImpl.sendRegisterEmail(request.getEmail(), request.getUsername(), jwtToken, uri);
		} catch (Exception e) {
			throw new Exception(ErrorMessage.AUTH_INVALID_EMAIL_REGISTER);
		}
	}
	
	public AuthenticationResponse login(AuthenticationRequest request) {
		try {
	    Authentication auth = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                    request.getUsername(),
	                    request.getPassword()
	            )
	    );
		}catch (Exception e) {
//			if(auth.isAuthenticated()) {
		    	//
			return AuthenticationResponse.builder().build();
//		    }
		}
	    
	    User user = userRepository.findByUserName(request.getUsername())
	            .orElseThrow();
	    
		if (!user.getIsActive()) {
			return AuthenticationResponse.builder().build();
		}
		
		List<Token> loginTokens= tokenRepository.getAllByUserId(user.getId());
		if(!loginTokens.equals(null))
		{
			for (Token token : loginTokens) {
				token.setExpired(true);
			}
			tokenRepository.saveAll(loginTokens);
		}
		
	    var jwtToken = jwtService.generateToken(user);
	    Token token = Token.builder()
	            .userId(user.getId())
	            .token(jwtToken)
	            .expired(false)
	            .revoked(false)
	            .build();
	    tokenRepository.save(token);
	    UserDto userDto = UserDto.builder()
	    		.username(user.getUsername())
	    		.password(null)
	    		.role(user.getRole())
	    		.build();
	    return AuthenticationResponse.builder()
	            .userDto(userDto)
	            .token(jwtToken)
	            .timeAlive(JwtService.TIME_ALIVE)
	            .build();
	}

	public boolean verifyConfirm(String token) throws Exception {
		if (token.isEmpty())
			return false;
		String username = jwtService.extractUsername(token);
		Optional<User> user = userRepository.findNotActiveUser(username, token);
		if (username != null && user.isEmpty()) {
			throw new Exception(CommonMessage.AUTH_ACTIVED_USER);
		}
		if (!user.isEmpty()) {
			User activeUser = user.get();
			activeUser.setIsActive(true);
			userRepository.save(activeUser);
			return true;
		}
		return false;
	}
}
