package com.example.lab.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String username);
}
