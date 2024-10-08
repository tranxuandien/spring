package com.example.lab.enums;

public enum DeviceUsingRegister {
	Inprogress("Đăng ký"), Cancel("Hủy"), Done("Hoàn thành");

	private final String value;

	DeviceUsingRegister(String string) {
		this.value = string;
	}

	public String getVal() {
		return this.value;
	}

}
