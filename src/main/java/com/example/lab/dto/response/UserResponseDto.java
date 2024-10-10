package com.example.lab.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {

	Long id;
	String userName;

	public UserResponseDto(Long id, String name, String studentId) {
		this.id = id;
		this.userName = (studentId != null && !studentId.isEmpty()) ? name + "-" + studentId : name;
	}
}
