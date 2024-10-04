package com.example.lab.dto.request;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PrintBarcodeChemicalRequestDto {
	List<PrintBarcodeChemicalDto> list;
}
