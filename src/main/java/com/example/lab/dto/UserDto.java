package com.example.lab.dto;

import com.example.lab.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private String username;
	private String password;
	private String role;

	public UserDto(User chemicalImportUser) {
		// TODO Auto-generated constructor stub
	}

}
