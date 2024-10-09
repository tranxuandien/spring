package com.example.lab.enums;

public enum Roles {
	
	USER("ROLE_USER"),
	BUDDY("ROLE_BUDDY"),
	ADMIN("ROLE_ADMIN");

	private final String value;

	Roles(String string) {
		this.value = string;
	}

	public String getVal() {
		return this.value;
	}

}
