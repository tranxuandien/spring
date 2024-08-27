package com.example.lab.enums;

public enum ChemicalInventoryStatus {
	Warning("Sắp hết"),Old("Hết"), New("Mới");

	private final String value;

	ChemicalInventoryStatus(String string) {
		this.value = string;
	}

	public String getVal() {
		return this.value;
	}

}
