package com.example.lab.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
}

