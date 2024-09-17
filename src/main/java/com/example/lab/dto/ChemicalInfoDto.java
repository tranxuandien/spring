package com.example.lab.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.lab.enums.ChemicalInventoryStatus;
import com.example.lab.model.ChemicalInfo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChemicalInfoDto {

	private static final BigDecimal LIMIT_ALERT = BigDecimal.ZERO;
	private static final BigDecimal LIMIT_WARNING = BigDecimal.valueOf(100);

	public Long id;
	@NotEmpty(message = "Nhập thông tin tên hóa chất")
	public String name;
	public String brand;
	public String chemicalType;
	public String chemicalTypeInfo;
	@NotNull
	public BigDecimal manufactoryQuantity;
	@NotNull(message = "Nhập hạn sử dụng")
	@NotEmpty(message = "Nhập hạn sử dụng")
	public String expiredDate;
	public String chemicalClass;
	public String chemicalClassInfo;
	public String otherInfo;
	@NotNull
	public String registerUser;
	@NotNull
	public String position;
	public String chemicalUsingUser;
	public String chemicalStatus;
	public String purchaseSrc;
	public LocalDate createAt;
	public LocalDate updateAt;
	public BigDecimal remain;
	public String impExpInfo;

	public ChemicalInfoDto() {
		super();
	}

	public ChemicalInfoDto(Long id, String name, String brand, String chemicalType,
			String chemicalTypeInfo, BigDecimal manufactoryQuantity, String expiredDate,
			String chemicalClass, String chemicalClassInfo, String otherInfo, String registerUser, String position,
			String impExpInfo, String chemicalUsingUser, String chemicalStatus, String purchaseSrc, LocalDate createAt,
			LocalDate updateAt, BigDecimal remain) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.chemicalType = chemicalType;
		this.chemicalTypeInfo = chemicalTypeInfo;
		this.manufactoryQuantity = manufactoryQuantity;
		this.expiredDate = expiredDate;
		this.chemicalClass = chemicalClass;
		this.chemicalClassInfo = chemicalClassInfo;
		this.otherInfo = otherInfo;
		this.registerUser = registerUser;
		this.position = position;
		this.chemicalUsingUser = chemicalUsingUser;
		this.chemicalStatus = chemicalStatus;
		this.purchaseSrc = purchaseSrc;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.remain = remain;
	}

	public ChemicalInfoDto(ChemicalInfo chemicalInfo) {
		this.id = chemicalInfo.getId();
		this.name = chemicalInfo.getName();
		this.brand = chemicalInfo.getBrand().getName();
		this.chemicalType = chemicalInfo.getChemicalType();
		this.chemicalTypeInfo = chemicalInfo.getChemicalTypeInfo();
		this.manufactoryQuantity = chemicalInfo.getManufactoryQuantity();
		this.chemicalClass = chemicalInfo.getChemicalClass();
		this.chemicalClassInfo = chemicalInfo.getChemicalClassInfo();
		this.otherInfo = chemicalInfo.getOtherInfo();
//		this.registerUser = chemicalInfo.getChemicalImpExp().getImpUser().getName();
		this.position = chemicalInfo.getPosition().getPositionInfo();
		this.chemicalStatus = chemicalInfo.getChemicalStatus();
		this.purchaseSrc = chemicalInfo.getPurchaseSrc();
		this.createAt = chemicalInfo.getCreateAt();
		this.updateAt = chemicalInfo.getUpdateAt();
		//TODO
//		this.remain = chemicalInfo.get;
	}

	public void updateImpExpInfo() {
		if (this.remain.compareTo(LIMIT_WARNING) > 0)
			this.impExpInfo = ChemicalInventoryStatus.New.getVal();
		else if (this.remain.compareTo(LIMIT_ALERT) > 0)
			this.impExpInfo = ChemicalInventoryStatus.Warning.getVal();
		else
			this.impExpInfo = ChemicalInventoryStatus.Old.getVal();
	}
}
