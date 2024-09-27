package com.example.lab.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {

	Long id;
	String userName;

	public UserResponseDto(Long id, String firstName, String lastName) {
		this.id = id;
		this.userName = firstName + " " + lastName;
	}
}
