package com.example.lab.dto;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

import com.example.lab.enums.ChemicalInventoryStatus;

public class SearchChemicalDto {

	private Long id;
	private String chemicalType;
	private String impExpType;
	private String name;

	private BigDecimal range1;
	private BigDecimal range2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getName() {
		return StringUtils.hasLength(name) ? name : null;
	}

	public void setName(String name) {
		this.name = name;
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
