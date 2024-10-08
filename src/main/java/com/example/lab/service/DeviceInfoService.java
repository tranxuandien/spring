package com.example.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab.dto.request.DeviceRegisterRequestDto;
import com.example.lab.dto.request.SearchDeviceInfoRequestDto;
import com.example.lab.dto.response.DeviceInfoMasterResponseDto;
import com.example.lab.dto.response.DeviceInfoResponseDto;
import com.example.lab.model.DeviceInfo;
import com.example.lab.repository.DeviceInfoRepository;

@Service
public class DeviceInfoService {

	@Autowired
	DeviceInfoRepository deviceInfoRepository;
	
	public List<DeviceInfoResponseDto> getAllData() {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(DeviceRegisterRequestDto dto) throws Exception {
		DeviceInfo info = new DeviceInfo(dto);
		if (!deviceInfoRepository.findByDeviceName(dto.getName().trim()).isEmpty())
			throw new Exception();
		deviceInfoRepository.save(info);
	}

	public List<DeviceInfoResponseDto> getListDeviceInfo(SearchDeviceInfoRequestDto dto) {
		return deviceInfoRepository.getAllInfo(dto.getName(),dto.getPositionId());
	}

	public List<DeviceInfoMasterResponseDto> getListMasterDeviceInfo() {
		return deviceInfoRepository.getListMaster();
	}
}
