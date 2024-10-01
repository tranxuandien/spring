package com.example.lab.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionRegisterRequestDto {
	private String lab;
	private String room;
	private String ray; 
}
