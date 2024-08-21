package com.example.lab.dto;

import java.util.Date;

import com.example.lab.model.UserInfo;

public class UserInfoDto {

	public Integer id;
	public String name;
	public String address;
	public String email;
	public Date createAt;
	public Date updateAt;

	public UserInfoDto(UserInfo userInfo) {
		this.name = userInfo.getName();
		this.address = userInfo.getAddress();
		this.email = userInfo.getEmail();
		this.createAt = userInfo.getCreateAt();
		this.updateAt = userInfo.getUpdateAt();
	}

	public UserInfoDto(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

}
