package com.example.lab.enums;

public enum ChemicalType {

	LIQUID("Dung dịch"), POWDER("Bột");

	private final String name;

	ChemicalType(String name) {
		this.name = name;
	}

	public String getVal() {
		return this.name;
	}

}
