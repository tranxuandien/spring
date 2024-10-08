package com.example.lab.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceInfoResponseDto {
	public Long id;
	public String name;
	public String position;
	public String otherInfo;
	public String user;
	public Boolean deviceStatus;
}
