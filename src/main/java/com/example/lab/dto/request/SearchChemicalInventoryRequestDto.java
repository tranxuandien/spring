package com.example.lab.dto.request;

import java.math.BigDecimal;

import com.example.lab.dto.ChemicalDto;
import com.example.lab.enums.ChemicalInventoryStatus;

import lombok.Data;

@Data
public class SearchChemicalInventoryRequestDto {

	public ChemicalDto chemical;
	public String brand;
	public String chemicalType;
	public String chemicalClass;
	public String position;
	public String impExpType;

	private BigDecimal range1;
	private BigDecimal range2;

	public void setRangeSearch() {
		if (this.impExpType == null)
			return;
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
