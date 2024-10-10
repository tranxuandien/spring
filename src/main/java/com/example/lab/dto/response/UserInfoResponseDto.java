package com.example.lab.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoResponseDto {
	public Long id;
	public String name;
	public String address;
	public String buddy;
	public String email;
	public String role;
	public Boolean isActive;
}
