package com.example.lab.service.securityServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab.model.User;
import com.example.lab.repository.user.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getAll() {
		return userRepository.findAll();
	}
}
