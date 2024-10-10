package com.example.lab.service.securityServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab.dto.BuddyInfoDto;
import com.example.lab.dto.request.BuddyRegisterRequestDto;
import com.example.lab.dto.response.CommonSelectResponseDto;
import com.example.lab.dto.response.UserInfoResponseDto;
import com.example.lab.dto.response.UserResponseDto;
import com.example.lab.enums.Roles;
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

    
	public List<UserInfoResponseDto> getUsers(String name) {
		return userRepository.findAllUserInfo(name).stream()
				.filter(i -> !i.getRole().equals(Roles.ADMIN.getVal().substring(5))).toList();
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


	public List<UserResponseDto> getAllUser() {
		return userRepository.findAllUser();
	}


	public List<CommonSelectResponseDto> getUsersRoleBuddy() {
		return userRepository.getUsersBuddy(Roles.BUDDY.getVal());
	}


	public List<CommonSelectResponseDto> getUsersRoleUser() {
		return userRepository.getUsersStudent(Roles.USER.getVal());
	}


	public void buddyRegister(BuddyRegisterRequestDto dto) {
		userRepository.buddyRegister(dto.getBuddy(),dto.getUsers());
	}


	public void active(Long id) {
		userRepository.active(id);
	}
	
	public void delete(Long id) {
		userRepository.delete(id);
	}


	public BuddyInfoDto findBuddyInfo(Long userId) {
		return userRepository.getBuddyInfo(userId);
	}
}

