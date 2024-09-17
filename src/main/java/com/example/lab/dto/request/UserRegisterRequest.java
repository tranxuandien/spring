package com.example.lab.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
	private String username;
	private String password;
	private String role;
	private String email;
	private String firstName;
	private String lastName;
	private String address;
}
