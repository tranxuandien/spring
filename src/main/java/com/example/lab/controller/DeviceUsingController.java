package com.example.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab.dto.response.CommonResponseEntity;
import com.example.lab.dto.response.DeviceUsingInfoResponseDto;
import com.example.lab.service.DeviceUsingService;

@RestController
@RequestMapping("api/v1")
public class DeviceUsingController {

	@Autowired
	DeviceUsingService deviceUsingService;
	
	@GetMapping("/device/using/list")
	public ResponseEntity<?> getListDevice() {
		List<DeviceUsingInfoResponseDto> dtos = deviceUsingService.getListUsingDeviceUser();
		return ResponseEntity.status(HttpStatus.OK).body(CommonResponseEntity.builder().data(dtos).build());
	}
}
