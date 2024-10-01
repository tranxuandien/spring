package com.example.lab.dto.request;

import com.example.lab.dto.ChemicalDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchChemicalInfoRequestDto {
	private ChemicalDto chemical;
	private Long brand;
	private String chemicalType;
	private String chemicalClass;
//	private String chemicalClassInfo;
}
