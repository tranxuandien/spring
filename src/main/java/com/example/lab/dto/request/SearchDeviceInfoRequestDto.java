package com.example.lab.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchDeviceInfoRequestDto {
	public String name;
	public Long positionId;
}
