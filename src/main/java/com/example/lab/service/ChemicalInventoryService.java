package com.example.lab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab.dto.request.SearchChemicalInventoryRequestDto;
import com.example.lab.dto.response.ChemicalInventoryResponseDto;
import com.example.lab.model.ChemicalInventory;
import com.example.lab.repository.ChemicalInventoryRepository;

@Service
public class ChemicalInventoryService {

	@Autowired
	ChemicalInventoryRepository chemicalInventoryRepository;
	
	public List<ChemicalInventoryResponseDto> getListChemicalInventory(SearchChemicalInventoryRequestDto search) {
		search.setRangeSearch();
		return chemicalInventoryRepository.getAllInfo(search.getChemical().getChemicalName(), search.getBrand(),
				search.getChemicalType(), search.getChemicalClass(), search.getPosition(), search.getRange1(),
				search.getRange2());
	}

	public ChemicalInventory deleteById(Long id) {
		Optional<ChemicalInventory> chemical = chemicalInventoryRepository.byId(id);
		if (chemical.isEmpty())
			return null;
		ChemicalInventory updateObj = chemical.get();
		updateObj.setIsDelete("1");// set delete
		chemicalInventoryRepository.save(updateObj);
		return updateObj;
	}

}
