package com.example.lab.common.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.example.lab.model.User;

public class RoleUtils {

	public static boolean hasRoleBuddy() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString()
				.contains("ROLE_BUDDY");
	}

	public static boolean hasRoleAdmin() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString()
				.contains("ROLE_ADMIN");
	}

	public static User getCurrentUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
