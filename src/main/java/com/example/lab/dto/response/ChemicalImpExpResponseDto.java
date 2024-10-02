package com.example.lab.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.example.lab.common.report.BarCodePDFExporter;

import lombok.Data;

@Data
//@AllArgsConstructor
public class ChemicalImpExpResponseDto {

	public String name;
	public String brand;
	public BigDecimal quantity;
	public String usingDate;
	public String impExpUser;
	public String barcode;
	public String chemicalType;
	public String chemicalTypeInfo;
	public String chemicalClass;
	public String chemicalClassInfo;
	public String position;
	public String impExpType;
	
	
	public ChemicalImpExpResponseDto(String name, String brand, BigDecimal quantity, LocalDateTime usingDate,
			String chemicalType, String chemicalTypeInfo, String chemicalClass, String chemicalClassInfo,
			String position, String impExpType, String firstName, String lastName, Long chemicalId, String lotNo) {
		if (chemicalId != null && lotNo != null) {
			this.barcode = "0".repeat(BarCodePDFExporter.CHEMICAL_CODE_LENGTH - chemicalId.toString().length())
					+ chemicalId.toString() + "0".repeat(BarCodePDFExporter.CHEMICAL_LOT_LENGTH - lotNo.length())
					+ lotNo;
		}
		this.name = name;
		this.brand = brand;
		this.quantity = quantity;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		OffsetDateTime off = OffsetDateTime.of(usingDate, ZoneOffset.UTC);
		ZonedDateTime zoned = off.atZoneSameInstant(ZoneId.of("Asia/Ho_Chi_Minh"));
		this.usingDate = zoned.format(formatter);
		this.impExpUser = firstName + " " + lastName;
		this.chemicalType = chemicalType;
		this.chemicalTypeInfo = chemicalTypeInfo;
		this.chemicalClass = chemicalClass;
		this.chemicalClassInfo = chemicalClassInfo;
		this.position = position;
		this.impExpType = impExpType;
	}
	
	
}
	
	
	
	
