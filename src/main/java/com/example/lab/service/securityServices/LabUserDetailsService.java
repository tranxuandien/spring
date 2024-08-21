package com.example.lab.service.securityServices;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.lab.model.User;
import com.example.lab.model.security.CustomUser;
import com.example.lab.repository.user.UserRepository;

@Service
public class LabUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder BCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
//		String password = BCryptPasswordEncoder.encode("123456");
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUser(user.getUserName(), user.getPassword(),
				new ArrayList<>(),user.getId());
	}

}
