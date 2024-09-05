package com.example.lab.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab.model.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {
}

