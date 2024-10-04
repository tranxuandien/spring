package com.example.lab.dto.request;

import com.example.lab.dto.ChemicalDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PrintBarcodeChemicalDto {
	ChemicalDto chemical;
	Integer printNumber;
	String[] printLst;
}
