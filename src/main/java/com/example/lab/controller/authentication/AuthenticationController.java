package com.example.lab.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab.dto.request.AuthenticationRequest;
import com.example.lab.dto.request.UserRegisterRequest;
import com.example.lab.dto.response.AuthenticationResponse;
import com.example.lab.service.securityServices.AuthenticationService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody UserRegisterRequest registerRequest) {
		AuthenticationResponse response = authenticationService.register(registerRequest);
		return ResponseEntity.status(response.getErrorMessage()==null?HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
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
