package com.example.lab.dto;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

import com.example.lab.enums.ChemicalInventoryStatus;

import lombok.Data;

@Data
public class SearchChemicalDto {

//	private Long id;
//	private String name;
	private ChemicalDto chemical;
	private String brand;
	private String chemicalType;
	private String chemicalClass;
	private String position;
	private String impExpType;

	private BigDecimal range1;
	private BigDecimal range2;


	

	public String getChemicalType() {
		return StringUtils.hasLength(chemicalType) ? chemicalType : null;
	}

	public void setChemicalType(String chemicalType) {
		this.chemicalType = chemicalType;
	}

	public String getImpExpType() {
		return StringUtils.hasLength(impExpType) ? impExpType : null;
	}

	public void setImpExpType(String impExpType) {
		this.impExpType = impExpType;
	}

	public BigDecimal getRange1() {
		return range1;
	}

	public void setRange1(BigDecimal range1) {
		this.range1 = range1;
	}

	public BigDecimal getRange2() {
		return range2;
	}

	public void setRange2(BigDecimal range2) {
		this.range2 = range2;
	}

	public void setRangeSearch() {
		if (this.impExpType == null) return;
		if (this.impExpType.equals(ChemicalInventoryStatus.New.getVal())) {
			this.range1 = BigDecimal.valueOf(100);
		} else if (this.impExpType.equals(ChemicalInventoryStatus.Warning.getVal())) {
			this.range1 = BigDecimal.valueOf(0);
			this.range2 = BigDecimal.valueOf(100);
		} else if (this.impExpType.equals(ChemicalInventoryStatus.Old.getVal())) {
			this.range2 = BigDecimal.valueOf(0);
		}
	}

}
