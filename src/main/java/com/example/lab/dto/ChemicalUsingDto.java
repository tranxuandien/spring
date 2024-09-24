package com.example.lab.dto;

import java.math.BigDecimal;

import com.example.lab.model.ChemicalInfo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChemicalUsingDto {
	public String name;
	@NotEmpty(message = "Nhập thông tin code")
	public String barcode;
	@NotNull(message = "Nhập khối lượng sử dụng")
	public BigDecimal quantity;

	public ChemicalUsingDto() {
		super();
	}

	public ChemicalUsingDto(ChemicalInfo chemicalInfo) {
		this.name = chemicalInfo.getName();
//		this.barcode = chemicalInfo.getId();
	}
}
