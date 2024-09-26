package com.example.lab.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.lab.common.message.CommonMessage;
import com.example.lab.common.message.ErrorMessage;
import com.example.lab.dto.request.AuthenticationRequest;
import com.example.lab.dto.request.UserRegisterRequest;
import com.example.lab.dto.response.AuthenticationResponse;
import com.example.lab.dto.response.CommonResponseEntity;
import com.example.lab.service.TokenService;
import com.example.lab.service.securityServices.AuthenticationService;

import jakarta.mail.SendFailedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private TokenService tokenService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserRegisterRequest registerRequest,
			HttpServletRequest request) throws Throwable {
		String uri = ServletUriComponentsBuilder.fromRequestUri(request).build().toUriString();
		try {
			 authenticationService.register(registerRequest, uri);
		} catch (SendFailedException |MailAuthenticationException auth) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.AUTH_INVALID_EMAIL_REGISTER).build());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body(CommonResponseEntity.builder().errorMessage(e.getMessage()).build());
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(CommonResponseEntity.builder().message(CommonMessage.AUTH_REGISTED_USER).build());
	}

	@GetMapping("/register/active")
	public ResponseEntity<?> activeUser(@PathParam("token") String token) {
		if (authenticationService.verifyConfirm(token))
			return ResponseEntity.status(HttpStatus.OK)
					.body(CommonResponseEntity.builder().message(CommonMessage.AUTH_ACTIVED_USER).build());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
				.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.AUTH_NOT_ACTIVE_USER).build());
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest request, HttpServletResponse res) {
		AuthenticationResponse response = authenticationService.login(request);
		if (response.getToken() == null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponseEntity.builder().errorMessage(ErrorMessage.AUTH_INVALID_USERNAME_PASSWORD).build());
//		Cookie cookie = new Cookie("token", response.getToken());
//		cookie.setHttpOnly(true);
//		res.addCookie(cookie);
		return ResponseEntity.ok(CommonResponseEntity.builder().data(response).build());
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(@PathParam("token") String token) {
		tokenService.setExpired(token);
		return ResponseEntity.ok(token);
	}
}
