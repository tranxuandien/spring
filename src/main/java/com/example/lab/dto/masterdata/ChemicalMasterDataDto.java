package com.example.lab.dto.masterdata;

import lombok.Data;

@Data
public class ChemicalMasterDataDto {

	private Long id;
	private String chemicalName;
	
	public ChemicalMasterDataDto(Long id, String chemicalName) {
		super();
		this.id = id;
		this.chemicalName = chemicalName;
	}
}
