package com.example.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab.dto.request.DeviceRegisterRequestDto;
import com.example.lab.dto.request.DeviceUsingRequestDto;
import com.example.lab.dto.request.SearchDeviceInfoRequestDto;
import com.example.lab.dto.response.DeviceInfoMasterResponseDto;
import com.example.lab.dto.response.DeviceInfoResponseDto;
import com.example.lab.model.DeviceInfo;
import com.example.lab.model.DeviceUsingInfo;
import com.example.lab.repository.DeviceInfoRepository;
import com.example.lab.repository.DeviceUsingInfoRepository;

@Service
public class DeviceInfoService {

	@Autowired
	DeviceInfoRepository deviceInfoRepository;
	
	@Autowired
	DeviceUsingInfoRepository deviceUsingInfoRepository;

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

	public DeviceUsingInfo use(DeviceUsingRequestDto dto) {
		DeviceUsingInfo info = new DeviceUsingInfo(dto);
		Boolean isAvailable = checkBusyDevice(info);
		if(isAvailable)
		return deviceUsingInfoRepository.save(info);
		else
			return null;
	}

	private Boolean checkBusyDevice(DeviceUsingInfo info) {
		List<DeviceUsingInfo> devices = deviceUsingInfoRepository.getBusyDevices(info.getDeviceId(),info.getStart(),info.getEnd(),info.getUserId());
		return devices.isEmpty();
	}

}
