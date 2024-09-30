package com.example.lab.dto.response;

import java.math.BigDecimal;

import com.example.lab.common.report.BarCodePDFExporter;

import lombok.Data;

@Data
public class ChemicalInventoryResponseDto {

	public String name;
	public String brand;
	public String chemicalType;
	public String chemicalTypeInfo;
	public String chemicalClass;
	public String chemicalClassInfo;
	public BigDecimal remain;
	public String position;
	public String expiredDate;
	public String impExpInfo;
	public String chemicalStatus;
	public String purchaseSrc;
	public Long id;
	public String lotNo;
	public String chemicalId;
	public String originName;
	public String barcode;

	public ChemicalInventoryResponseDto(Long id,String name, String brand, String chemicalType, String chemicalTypeInfo,
			String chemicalClass, String chemicalClassInfo, BigDecimal remain, String position, String expiredDate,
			String impExpInfo, String chemicalStatus, String purchaseSrc, Long chemicalId, String lotNo) {
		if (chemicalId != null && lotNo != null) {
			this.name = name + "-("
					+ "0".repeat(BarCodePDFExporter.CHEMICAL_CODE_LENGTH - chemicalId.toString().length())
					+ chemicalId.toString() + "0".repeat(BarCodePDFExporter.CHEMICAL_LOT_LENGTH - lotNo.length())
					+ lotNo + ")";
		} else
			this.name = name;
		this.brand = brand;
		this.chemicalType = chemicalType;
		this.chemicalTypeInfo = chemicalTypeInfo;
		this.chemicalClass = chemicalClass;
		this.chemicalClassInfo = chemicalClassInfo;
		this.remain = remain;
		this.position = position;
		this.expiredDate = expiredDate;
		this.impExpInfo = impExpInfo;
		this.chemicalStatus = chemicalStatus;
		this.purchaseSrc = purchaseSrc;
		this.id = id;
		this.originName = name;
		this.barcode = "0".repeat(BarCodePDFExporter.CHEMICAL_CODE_LENGTH - chemicalId.toString().length())
				+ chemicalId.toString() + "0".repeat(BarCodePDFExporter.CHEMICAL_LOT_LENGTH - lotNo.length()) + lotNo;
	}
}
