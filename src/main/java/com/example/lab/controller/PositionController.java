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
import com.example.lab.dto.PositionInfoDto;
import com.example.lab.dto.request.PositionRegisterRequestDto;
import com.example.lab.dto.response.CommonResponseEntity;
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
	
	@PostMapping("/buddy/position/add")
	public ResponseEntity<?> addPosition(@RequestBody PositionRegisterRequestDto dto) {
		try {
			positionInfoService.add(dto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.POSITION_CANNOT_ADD).build());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponseEntity.builder().message(CommonMessage.POSITION_CREATED).build());
	}
	
	@DeleteMapping("/buddy/position/delete/{id}")
	public ResponseEntity<?> deletePosition(@PathVariable(value = "id") Long id) {
		try {
			positionInfoService.deleteById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.POSITION_INFO_CANNOT_DELETED).build());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(CommonResponseEntity.builder().message(CommonMessage.POSITION_INFO_DELETED).build());
	}
}
