package com.example.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab.common.message.CommonMessage;
import com.example.lab.common.message.ErrorMessage;
import com.example.lab.dto.request.DeviceRegisterRequestDto;
import com.example.lab.dto.request.DeviceUsingRequestDto;
import com.example.lab.dto.request.SearchDeviceInfoRequestDto;
import com.example.lab.dto.response.CommonResponseEntity;
import com.example.lab.dto.response.DeviceInfoMasterResponseDto;
import com.example.lab.dto.response.DeviceInfoResponseDto;
import com.example.lab.model.DeviceUsingInfo;
import com.example.lab.service.DeviceInfoService;

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

	@PostMapping("/admin/device/add")
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
	
	@PostMapping("/device/using")
	public ResponseEntity<?> useDevice(@RequestBody DeviceUsingRequestDto dto) {
		try {
			DeviceUsingInfo info = deviceInfoService.use(dto);
			if(info==null)
				return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.DEVICE_NOT_AVAILABE).build()); 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.DEVICE_CANNOT_USE).build());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(CommonResponseEntity.builder().message(CommonMessage.DEVICE_USED).build());
	}
}
