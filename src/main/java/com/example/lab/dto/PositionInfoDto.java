package com.example.lab.dto;

import lombok.Data;

@Data
public class PositionInfoDto {

	private Long id;
	private String positionInfo;

	public PositionInfoDto(Long id, String positionInfo) {
		this.id = id;
		this.positionInfo = positionInfo;
	}
}
