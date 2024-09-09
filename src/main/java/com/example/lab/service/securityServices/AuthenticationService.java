package com.example.lab.service.securityServices;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.lab.dto.UserDto;
import com.example.lab.dto.request.AuthenticationRequest;
import com.example.lab.dto.request.UserRegisterRequest;
import com.example.lab.dto.response.AuthenticationResponse;
import com.example.lab.model.Token;
import com.example.lab.model.User;
import com.example.lab.repository.user.TokenRepository;
import com.example.lab.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(UserRegisterRequest request) {
		User newUser = new User();
		newUser.setUserName(request.getUsername());
		newUser.setPassword(passwordEncoder.encode(request.getPassword()));
		newUser.setRole(request.getRole());
		//check duplicate
		User createdUser = userRepository.save(newUser);
		String jwtToken = jwtService.generateToken(createdUser);
		Token token = new Token(jwtToken, "Bearer", false, false, createdUser.getId());
		tokenRepository.save(token);
		return AuthenticationResponse.builder().token(jwtToken)
				.userDto(UserDto.builder().username(createdUser.getUsername()).role(createdUser.getRole()).build())
				.build();
	}
	
	public AuthenticationResponse login(AuthenticationRequest request) {
	    authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                    request.getUsername(),
	                    request.getPassword()
	            )
	    );
	    User user = userRepository.findByUserName(request.getUsername())
	            .orElseThrow();
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
	            .build();
	}
}
