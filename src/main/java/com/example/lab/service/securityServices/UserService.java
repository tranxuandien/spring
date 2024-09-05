package com.example.lab.service.securityServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab.model.User;
import com.example.lab.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	@Autowired
    private UserRepository userRepository;

    
    public User createUser(User user) {
        return userRepository.save(user);
    }

    
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    
    public User updateUser(Long userId, User user) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setUserName(user.getUsername());
//            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
//            existingUser.setEmail(user.getEmail());
//            existingUser.setFirstName(user.getFirstName());
//            existingUser.setLastName(user.getLastName());
            return userRepository.save(existingUser);
        }
        return null;
    }
}

