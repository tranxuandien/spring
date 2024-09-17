package com.example.lab.service.securityServices;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.lab.model.User;
import com.example.lab.model.security.CustomUser;
import com.example.lab.repository.user.UserRepository;

@Service
public class LabUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optUser = userRepository.findByUserName(username);
		if (optUser.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		User user = optUser.get();
		return new CustomUser(user.getUsername(), user.getPassword(), user.getAuthorities(), user.getId());
	}

}
