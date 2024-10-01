package com.example.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab.common.message.CommonMessage;
import com.example.lab.common.message.ErrorMessage;
import com.example.lab.dto.BrandDto;
import com.example.lab.dto.request.BrandRegisterRequestDto;
import com.example.lab.dto.response.CommonResponseEntity;
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
	
	@PostMapping("/admin/brand/add")
	public ResponseEntity<?> addBrand(@RequestBody BrandRegisterRequestDto dto) {
		try {
			brandService.add(dto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.BRAND_CANNOT_ADD).build());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponseEntity.builder().message(CommonMessage.BRAND_CREATED).build());
	}
	
	@DeleteMapping("/admin/brand/delete/{id}")
	public ResponseEntity<?> deleteBrand(@PathVariable(value = "id") Long id) {
		try {
			brandService.deleteById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.BRAND_INFO_CANNOT_DELETED).build());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(CommonResponseEntity.builder().message(CommonMessage.BRAND_INFO_DELETED).build());
	}
}
