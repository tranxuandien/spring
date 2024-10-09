package com.example.lab.dto.request;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceUsingRequestDto {
	public Long deviceId;
	public String start;
	public String end;
	public Boolean deviceStatus;
	public String info;
	public Long userId;
	public String expect;
	public BigDecimal centrifugeSpeed;
	public Boolean centrifuge;
	public List<Long> user;
}
