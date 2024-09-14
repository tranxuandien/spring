package com.example.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab.dto.PositionInfoDto;
import com.example.lab.service.PositionInfoService;

@RestController
@RequestMapping("api/v1")
public class PositionController {

	@Autowired
	private PositionInfoService positionInfoService;

	@GetMapping("/position")
	public ResponseEntity<?> getAllPosition() {
		List<PositionInfoDto> dto = positionInfoService.getAllMasterData();
		return ResponseEntity.ok(dto);
	}
}
