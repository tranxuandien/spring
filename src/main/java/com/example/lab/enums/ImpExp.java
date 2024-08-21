package com.example.lab.enums;

public enum ImpExp {
	Import("Nhập"), 
	Export("Xuất");

	private final String value;

	ImpExp(String string) {
		this.value = string;
	}

	public String getVal() {
		return this.value;
	}

}
