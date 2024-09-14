package com.example.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab.dto.BrandDto;
import com.example.lab.service.BrandService;

@RestController
@RequestMapping("api/v1")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@GetMapping("/brand")
	public ResponseEntity<?> getAllBrand() {
		List<BrandDto> dto = brandService.getAllMasterData();
		return ResponseEntity.ok(dto);
	}
}
