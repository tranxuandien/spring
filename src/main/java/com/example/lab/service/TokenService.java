package com.example.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab.model.Token;
import com.example.lab.repository.user.TokenRepository;

@Service
public class TokenService {

	@Autowired
	private TokenRepository tokenRepository;
	
	public void setExpired(String token) {
		tokenRepository.setTokenExpired1(token);
	}

	public Token findByToken(String token) {
		return tokenRepository.getByToken(token);
	}
}
