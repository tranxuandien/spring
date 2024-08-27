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

	private static final BigDecimal LIMIT_ALERT= BigDecimal.ZERO;
	private static final BigDecimal LIMIT_WARNING = BigDecimal.valueOf(100);

	public Integer id;
	@NotEmpty(message = "Nhập thông tin tên hóa chất")
	public String name;
	public String brand;
	@NotEmpty(message = "Nhập thông tin code")
	public String code;
	public String chemicalType;
	@NotNull(message = "Nhập khối lượng nhập/xuất")
	public BigDecimal quantity;
	public String chemicalTypeInfo;
	public String description;
	@NotNull(message = "Nhập hạn sử dụng")
	@NotEmpty(message = "Nhập hạn sử dụng")
	public String expiredDate;
	public String type1;
	public String type2;
	public String chemicalShpt;
	public String otherInfo;
	public String registerUser;
	public String position;
	public String impExpInfo;
	public String chemicalUsingUser;
	public String chemicalStatus;
	public String purchaseSrc;
	public LocalDate createAt;
	public LocalDate updateAt;
	public BigDecimal remain;

	public ChemicalInfoDto() {
		super();
	}

	public ChemicalInfoDto(Integer id, String name, String brand, String code, String chemicalType, BigDecimal quantity,
			String chemicalTypeInfo, String description, String expiredDate, String type1, String type2,
			String chemicalShpt, String otherInfo, String registerUser, String position, String impExpInfo,
			String chemicalUsingUser, String chemicalStatus, String purchaseSrc, LocalDate createAt, LocalDate updateAt,
			BigDecimal remain) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.code = code;
		this.chemicalType = chemicalType;
		this.quantity = quantity;
		this.chemicalTypeInfo = chemicalTypeInfo;
		this.description = description;
		this.expiredDate = expiredDate;
		this.type1 = type1;
		this.type2 = type2;
		this.chemicalShpt = chemicalShpt;
		this.otherInfo = otherInfo;
		this.registerUser = registerUser;
		this.position = position;
		this.impExpInfo = impExpInfo;
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
		this.code = chemicalInfo.getCode();
		this.chemicalType = chemicalInfo.getChemicalType();
		this.chemicalTypeInfo = chemicalInfo.getChemicalTypeInfo();
		this.description = chemicalInfo.getDescription();
		this.chemicalShpt = chemicalInfo.getChemicalShpt();
		this.otherInfo = chemicalInfo.getOtherInfo();
//		this.registerUser = chemicalInfo.getChemicalImpExp().getImpUser().getName();
		this.position = chemicalInfo.getPosition();
		this.impExpInfo = chemicalInfo.getChemicalImpExp().getType();
		this.chemicalStatus = chemicalInfo.getChemicalStatus();
		this.purchaseSrc = chemicalInfo.getPurchaseSrc();
		this.createAt = chemicalInfo.getCreateAt();
		this.updateAt = chemicalInfo.getUpdateAt();
		this.quantity = chemicalInfo.getChemicalImpExp().getQuantity();
		this.remain = chemicalInfo.getChemicalInventory().getQuantity();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getChemicalType() {
		return chemicalType;
	}

	public void setChemicalType(String chemicalType) {
		this.chemicalType = chemicalType;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getChemicalTypeInfo() {
		return chemicalTypeInfo;
	}

	public void setChemicalTypeInfo(String chemicalTypeInfo) {
		this.chemicalTypeInfo = chemicalTypeInfo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChemicalShpt() {
		return chemicalShpt;
	}

	public void setChemicalShpt(String chemicalShpt) {
		this.chemicalShpt = chemicalShpt;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getImpExpInfo() {
		return impExpInfo;
	}

	public void setImpExpInfo(String impExpInfo) {
		this.impExpInfo = impExpInfo;
	}

	public String getChemicalStatus() {
		return chemicalStatus;
	}

	public void setChemicalStatus(String chemicalStatus) {
		this.chemicalStatus = chemicalStatus;
	}

	public String getPurchaseSrc() {
		return purchaseSrc;
	}

	public void setPurchaseSrc(String purchaseSrc) {
		this.purchaseSrc = purchaseSrc;
	}

	public LocalDate getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDate createAt) {
		this.createAt = createAt;
	}

	public LocalDate getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDate updateAt) {
		this.updateAt = updateAt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getRegisterUser() {
		return registerUser;
	}

	public void setRegisterUser(String registerUser) {
		this.registerUser = registerUser;
	}

	public String getChemicalUsingUser() {
		return chemicalUsingUser;
	}

	public void setChemicalUsingUser(String chemicalUsingUser) {
		this.chemicalUsingUser = chemicalUsingUser;
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
