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
import com.example.lab.dto.request.DeviceRegisterRequestDto;
import com.example.lab.dto.request.SearchDeviceInfoRequestDto;
import com.example.lab.dto.response.CommonResponseEntity;
import com.example.lab.dto.response.DeviceInfoMasterResponseDto;
import com.example.lab.dto.response.DeviceInfoResponseDto;
import com.example.lab.service.DeviceInfoService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("api/v1")
public class DeviceController {

	@Autowired
	private DeviceInfoService deviceInfoService;

	@GetMapping("/device/list/master")
	public ResponseEntity<?> getListDevice() {
		List<DeviceInfoMasterResponseDto> dtos = deviceInfoService.getListMasterDeviceInfo();
		return ResponseEntity.status(HttpStatus.OK).body(CommonResponseEntity.builder().data(dtos).build());
	}
	
	@PostMapping("/device/list")
	public CommonResponseEntity getListDevice(@RequestBody SearchDeviceInfoRequestDto searchDeviceDto) {
		List<DeviceInfoResponseDto> dtos = deviceInfoService.getListDeviceInfo(searchDeviceDto);
		return CommonResponseEntity.builder().data(dtos).build();
	}

	@PostMapping("/buddy/device/add")
	public ResponseEntity<?> addDevice(@RequestBody DeviceRegisterRequestDto dto) {
		try {
			deviceInfoService.add(dto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.DEVICE_CANNOT_ADD).build());
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(CommonResponseEntity.builder().message(CommonMessage.DEVICE_CREATED).build());
	}
	
	@GetMapping("/buddy/device/active")
	public ResponseEntity<?> activeDevice(@PathParam("id") Long id) {
		try {
			deviceInfoService.active(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.DEVICE_CANNOT_ACTIVE).build());
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(CommonResponseEntity.builder().message(CommonMessage.DEVICE_ACTIVED).build());
	}
	
	@DeleteMapping("/buddy/device/delete/{id}")
	public ResponseEntity<?> deleteDevice(@PathVariable(value = "id") Long id) {
		try {
			deviceInfoService.deleteById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
					CommonResponseEntity.builder().errorMessage(ErrorMessage.DEVICE_CANNOT_DELETED).build());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(CommonResponseEntity.builder().message(CommonMessage.DEVICE_DELETED).build());
	}
}
