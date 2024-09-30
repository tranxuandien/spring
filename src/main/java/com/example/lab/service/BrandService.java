package com.example.lab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab.dto.BrandDto;
import com.example.lab.model.Brand;
import com.example.lab.repository.BrandRepository;

@Service
public class BrandService {

	@Autowired
	private BrandRepository brandRepository;

	public List<BrandDto> getAllMasterData() {
		
		return brandRepository.findAll().stream().map(item -> new BrandDto(item.getId(), item.getName())).toList();
	}

	public Optional<Brand> findByid(Long id) {
		return brandRepository.findById(id);
	}

}
