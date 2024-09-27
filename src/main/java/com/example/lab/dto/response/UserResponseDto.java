package com.example.lab.dto.response;

import com.example.lab.model.UserInfo;

import lombok.Data;

@Data
public class UserResponseDto {

	Long id;
	String userName;

	public UserResponseDto(UserInfo item) {
		this.id = item.getId();
		this.userName = item.getName();
	}
}
