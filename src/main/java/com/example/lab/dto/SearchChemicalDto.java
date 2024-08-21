package com.example.lab.dto;

import org.springframework.util.StringUtils;

public class SearchChemicalDto {

	private String code;
    private String chemicalType;
    private String impExpType;
    private String registerUser;
    
    
	public String getCode() {
		return StringUtils.hasLength(code)?code:null;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getChemicalType() {
		return StringUtils.hasLength(chemicalType)?chemicalType:null;
	}
	public void setChemicalType(String chemicalType) {
		this.chemicalType = chemicalType;
	}
	public String getImpExpType() {
		return StringUtils.hasLength(impExpType)?impExpType:null;
	}
	public void setImpExpType(String impExpType) {
		this.impExpType = impExpType;
	}
	public String getRegisterUser() {
		return StringUtils.hasLength(registerUser)?registerUser:null;
	}
	public void setRegisterUser(String registerUser) {
		this.registerUser = registerUser;
	}
    
    
}
