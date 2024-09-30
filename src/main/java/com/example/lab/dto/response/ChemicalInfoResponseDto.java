package com.example.lab.dto.response;

import com.example.lab.model.ChemicalInfo;
import com.example.lab.model.UserInfo;

import lombok.Data;

@Data
public class ChemicalInfoResponseDto {

	public Long id;
	public String name;
	public String brand;
	public String chemicalType;
	public String chemicalTypeInfo;
	public String chemicalClass;
	public String chemicalClassInfo;
	public String otherInfo;
	public String registerUser;
	
	public ChemicalInfoResponseDto(ChemicalInfo chemicalInfo,UserInfo user) {
		this.id = chemicalInfo.getId();
		this.name = chemicalInfo.getName();
		this.brand = chemicalInfo.getBrand().getName();
		this.chemicalType = chemicalInfo.getChemicalType();
		this.chemicalTypeInfo = chemicalInfo.getChemicalTypeInfo();
		this.chemicalClass = chemicalInfo.getChemicalClass();
		this.chemicalClassInfo = chemicalInfo.getChemicalClassInfo();
		this.otherInfo = chemicalInfo.getOtherInfo();
		this.registerUser = user==null?null:user.getName();
	}

	public ChemicalInfoResponseDto(Long id, String name, String brand, String chemicalType, String chemicalTypeInfo,
			String chemicalClass, String chemicalClassInfo, String otherInfo, String registerUser) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.chemicalType = chemicalType;
		this.chemicalTypeInfo = chemicalTypeInfo;
		this.chemicalClass = chemicalClass;
		this.chemicalClassInfo = chemicalClassInfo;
		this.otherInfo = otherInfo;
		this.registerUser = registerUser;
	}
}
