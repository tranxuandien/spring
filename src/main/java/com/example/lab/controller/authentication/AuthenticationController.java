package com.example.lab.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.lab.dto.request.AuthenticationRequest;
import com.example.lab.dto.request.UserRegisterRequest;
import com.example.lab.dto.response.AuthenticationResponse;
import com.example.lab.service.securityServices.AuthenticationService;

import jakarta.mail.SendFailedException;
import jakarta.servlet.http.Cookie;
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
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody UserRegisterRequest registerRequest,HttpServletRequest request) {
		String uri = ServletUriComponentsBuilder.fromRequestUri(request).build().toUriString();
		AuthenticationResponse response;
		try {
			response = authenticationService.register(registerRequest,uri);
		} catch (SendFailedException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body(AuthenticationResponse.builder().errorMessage("Error when create new user!").build());
		}
		return ResponseEntity.status(response.getErrorMessage()==null?HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
	}
	
	@GetMapping("/register/active")
	public ResponseEntity<AuthenticationResponse> activeUser (@PathParam("token") String token) {
		if(authenticationService.verifyConfirm(token))
			return ResponseEntity.status(HttpStatus.OK).body(AuthenticationResponse.builder().build());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(AuthenticationResponse.builder().build());
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest request, HttpServletResponse res) {
		AuthenticationResponse response = authenticationService.login(request);
		Cookie cookie = new Cookie("token", response.getToken());
		cookie.setHttpOnly(true);
		res.addCookie(cookie);
		return ResponseEntity.ok(response);
	}
}
