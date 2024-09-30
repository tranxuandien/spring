package com.example.lab.dto.response;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChemicalImpExpChartResponseDto {

	public Long userId;
	public String userName;
	public BigDecimal useQuantity;
	
	public ChemicalImpExpChartResponseDto roundQuantity() {
		this.useQuantity = this.useQuantity.setScale(2, RoundingMode.HALF_DOWN);
		return this;
	}
}
