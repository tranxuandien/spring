package com.example.lab.repository.user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab.model.Token;

import jakarta.transaction.Transactional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE Token t1 SET t1.expired = true,t1.updateAt=?2 WHERE t1.token = ?1")
	void setTokenExpired1(String token,LocalDateTime datetime);
	
	@Query("SELECT t1 FROM Token t1 WHERE t1.expired = false AND t1.userId = ?1")
	List<Token> getAllByUserId(Long id);

	@Query("SELECT t1 FROM Token t1 WHERE t1.token = ?1 AND t1.expired = false")
	Token getByToken(String token);
}

