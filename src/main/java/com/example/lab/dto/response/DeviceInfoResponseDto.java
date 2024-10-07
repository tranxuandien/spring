package com.example.lab.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceInfoResponseDto {
	public String name;
	public String position;
	public String otherInfo;
	public String user;
}
