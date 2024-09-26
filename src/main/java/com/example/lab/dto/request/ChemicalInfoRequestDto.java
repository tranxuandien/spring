package com.example.lab.dto.request;

import com.example.lab.model.ChemicalInfo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChemicalInfoRequestDto {

	public Long id;
	@NotNull(message = "Nhập tên hóa chất")
	@NotEmpty(message = "Nhập tên hóa chất")
	public String name;
	@NotNull(message = "Chọn nơi sản xuất")
	@NotEmpty(message = "Chọn nơi sản xuất")
	public String brand;
	@NotNull(message = "Chọn loại hóa chất")
	@NotEmpty(message = "Chọn loại hóa chất")
	public String chemicalType;
	@NotNull(message = "Chọn cách đóng gói")
	@NotEmpty(message = "Chọn cách đóng gói")
	public String chemicalTypeInfo;

	@NotNull(message = "Chọn cách phân loại hóa chất")
	@NotEmpty(message = "Chọn cách phân loại hóa chất")
	public String chemicalClass;
	public String chemicalClassInfo;
	public String otherInfo;
	@NotNull(message = "Nhập thông tin người đăng ký hóa chất")
	@NotEmpty(message = "Nhập thông tin người đăng ký hóa chất")
	public String registerUser;

	public ChemicalInfoRequestDto() {
		super();
	}

	public ChemicalInfoRequestDto(ChemicalInfo chemicalInfo) {
		this.id = chemicalInfo.getId();
		this.name = chemicalInfo.getName();
		this.brand = chemicalInfo.getBrand().getName();
		this.chemicalType = chemicalInfo.getChemicalType();
		this.chemicalTypeInfo = chemicalInfo.getChemicalTypeInfo();
		this.chemicalClass = chemicalInfo.getChemicalClass();
		this.chemicalClassInfo = chemicalInfo.getChemicalClassInfo();
		this.otherInfo = chemicalInfo.getOtherInfo();
		this.registerUser = chemicalInfo.getRegisterUser().getName();
	}

	public ChemicalInfoRequestDto(String name, String brand, String chemicalType, String chemicalTypeInfo,
			String chemicalClass, String chemicalClassInfo, String otherInfo, String registerUser) {
		super();
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
