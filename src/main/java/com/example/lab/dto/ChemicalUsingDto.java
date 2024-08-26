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
	public String code;
	@NotNull(message = "Nhập khối lượng sử dụng")
	public BigDecimal quantity;

	
	public ChemicalUsingDto() {
		super();
	}

	public ChemicalUsingDto(ChemicalInfo chemicalInfo) {
		this.name = chemicalInfo.getName();
		this.code = chemicalInfo.getCode();
		this.quantity = chemicalInfo.getChemicalImpExp().getQuantity();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
