package com.example.lab.dto.response;

import java.math.BigDecimal;

import com.example.lab.model.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChemicalUsingResponseDto {

	public String name;
	public String brand;
	public BigDecimal remain;
	public String chemicalType;
	public String chemicalTypeInfo;
	public String chemicalClass;
	public String chemicalClassInfo;
	public String position;
	public String chemicalStatus;
	public String otherInfo;
	public String registerUser;
	
	public ChemicalUsingResponseDto(String name, String brand, BigDecimal remain, String chemicalType,
			String chemicalTypeInfo, String chemicalClass, String chemicalClassInfo, String position,
			String chemicalStatus, String otherInfo, UserInfo user) {
		this.name = name;
		this.brand = brand;
		this.remain = remain;
		this.chemicalType = chemicalType;
		this.chemicalTypeInfo = chemicalTypeInfo;
		this.chemicalClass = chemicalClass;
		this.chemicalClassInfo = chemicalClassInfo;
		this.position = position;
		this.chemicalStatus = chemicalStatus;
		this.otherInfo = otherInfo;
		this.registerUser = user.getName();
	}
}
