package com.example.lab.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BrandDto {

	private Long id;
	private String name;
	private int zipCode;
	private Date createAt;
	private Date updateAt;

	public BrandDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
