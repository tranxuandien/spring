package com.example.lab.dto.request;

import com.example.lab.dto.ChemicalDto;

import lombok.Data;

@Data
public class SearchImpExpRequestDto {

	public ChemicalDto chemical;
	public String brand;
	public String chemicalType;
	public String chemicalClass;
	public String position;
	public UserDto user;
	
	public void init() {
		if (this.chemical == null)
			this.chemical = new ChemicalDto();
		if (this.user == null)
			this.user = new UserDto();
	}
}
