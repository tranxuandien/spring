package com.example.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab.common.message.CommonMessage;
import com.example.lab.common.message.ErrorMessage;
import com.example.lab.dto.request.DeviceUsingRequestDto;
import com.example.lab.dto.response.CommonResponseEntity;
import com.example.lab.dto.response.DeviceUsingInfoResponseDto;
import com.example.lab.model.DeviceUsingInfo;
import com.example.lab.service.DeviceUsingService;

import jakarta.websocket.server.PathParam;

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

	@GetMapping("/device/using/user")
	public ResponseEntity<?> getListRegisterByDevice(@PathParam("deviceId") Long deviceId) {
		List<DeviceUsingInfoResponseDto> dtos = deviceUsingService.getListRegisterByDevice(deviceId);
		return ResponseEntity.status(HttpStatus.OK).body(CommonResponseEntity.builder().data(dtos).build());
	}

	@PostMapping("/device/using")
	public ResponseEntity<?> useDevice(@RequestBody DeviceUsingRequestDto dto) {
		try {
			DeviceUsingInfo info = deviceUsingService.use(dto);
			if (info == null)
				return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
						.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.DEVICE_NOT_AVAILABE).build());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.DEVICE_CANNOT_USE).build());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(CommonResponseEntity.builder().message(CommonMessage.DEVICE_USED).build());
	}

	@GetMapping("/device/using/cancel/{id}")
	public ResponseEntity<?> cancelUsingDevice(@PathVariable("id") Long id) {
		DeviceUsingInfo device = deviceUsingService.cancelUsingDevice(id);
		if (device != null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(CommonResponseEntity.builder().message(CommonMessage.DEVICE_USING_CANCEL).build());
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
				.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.DEVICE_CANCEL_USING_FAIL).build());
	}
	
	@GetMapping("/device/using/done/{id}")
	public ResponseEntity<?> doneUsingDevice(@PathVariable("id") Long id) {
		DeviceUsingInfo device = deviceUsingService.doneUsingDevice(id);
		if (device != null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(CommonResponseEntity.builder().message(CommonMessage.DEVICE_USING_DONE).build());
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
				.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.DEVICE_DONE_USING_FAIL).build());
	}
}
