package com.example.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab.dto.request.SearchImpExpRequestDto;
import com.example.lab.dto.response.ChemicalImpExpChartResponseDto;
import com.example.lab.dto.response.ChemicalImpExpResponseDto;
import com.example.lab.repository.ChemicalImpExpRepository;

@Service
public class ChemicalImpExpService {

	@Autowired
	ChemicalImpExpRepository chemicalImpExpRepository;
	
	public List<ChemicalImpExpResponseDto> getListImpExpDetail(SearchImpExpRequestDto search) {
		return chemicalImpExpRepository.getAllHistory(search.getUser().getId(), search.getChemical().getChemicalName(),
				search.getBrand(), search.getChemicalType(), search.getChemicalClass(), search.getPosition());
	}

	public List<ChemicalImpExpChartResponseDto> getListImpExpChart(SearchImpExpRequestDto search) {
		return chemicalImpExpRepository
				.getAllHistoryForChart(search.getUser().getId(), search.getChemical().getChemicalName(),
						search.getBrand(), search.getChemicalType(), search.getChemicalClass(), search.getPosition())
				.stream().map(i -> i.roundQuantity()).toList();
	}

}
