package com.example.lab.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String username);
}
