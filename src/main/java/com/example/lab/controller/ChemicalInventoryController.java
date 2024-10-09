package com.example.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab.common.message.CommonMessage;
import com.example.lab.common.message.ErrorMessage;
import com.example.lab.dto.ChemicalDto;
import com.example.lab.dto.request.SearchChemicalInventoryRequestDto;
import com.example.lab.dto.response.ChemicalInventoryResponseDto;
import com.example.lab.dto.response.CommonResponseEntity;
import com.example.lab.model.ChemicalInventory;
import com.example.lab.service.ChemicalInventoryService;

@RestController
@RequestMapping("api/v1")
public class ChemicalInventoryController {

	@Autowired
	private ChemicalInventoryService chemicalInventoryService;
	
	@PostMapping("/buddy/chemical/inventory/list")
	public CommonResponseEntity getListChemicalInventory(@RequestBody SearchChemicalInventoryRequestDto search) {
		if(search.getChemical() == null) search.setChemical(new ChemicalDto());
		List<ChemicalInventoryResponseDto> dtos = chemicalInventoryService.getListChemicalInventory(search);
		return CommonResponseEntity.builder().data(dtos).build();
	}

	@DeleteMapping("/buddy/chemical/inventory/delete/{id}")
	public ResponseEntity<?> deleteChemicalInventory(@PathVariable(value = "id") Long id) {
		ChemicalInventory chemical = chemicalInventoryService.deleteById(id);
		if (chemical == null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.CHEMICAL_CANNOT_DELETED).build());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(CommonResponseEntity.builder().message(CommonMessage.CHEMICAL_DELETED).build());
	}
}
