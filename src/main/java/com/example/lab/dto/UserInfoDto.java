package com.example.lab.dto;

import java.time.LocalDate;

import com.example.lab.model.UserInfo;

public class UserInfoDto {

	public Long id;
	public String name;
	public String address;
	public String email;
	public LocalDate createAt;
	public LocalDate updateAt;

	public UserInfoDto(UserInfo userInfo) {
		this.name = userInfo.getLastName();
		this.address = userInfo.getAddress();
		this.createAt = userInfo.getCreateAt();
		this.updateAt = userInfo.getUpdateAt();
	}

	public UserInfoDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}

}
