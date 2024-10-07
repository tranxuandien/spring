package com.example.lab.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceUsingInfoResponseDto {

	public String device;
	public LocalDateTime start;
	public LocalDateTime end;
	public Boolean deviceStatus;
	public String info;
	public String user;
}
