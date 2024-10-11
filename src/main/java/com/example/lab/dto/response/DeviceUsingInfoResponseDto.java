package com.example.lab.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceUsingInfoResponseDto {
	public Long id;
	public String device;
	public LocalDateTime start;
	public LocalDateTime end;
	public String registerStatus;
	public String info;
	public String buddy;
	public BigDecimal centrifugeSpeed;
	public Object users;
	public String expect;
	public String registerUser;
}
