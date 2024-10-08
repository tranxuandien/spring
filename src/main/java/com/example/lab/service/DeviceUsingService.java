package com.example.lab.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.lab.dto.request.DeviceUsingRequestDto;
import com.example.lab.dto.response.DeviceUsingInfoResponseDto;
import com.example.lab.enums.DeviceUsingRegister;
import com.example.lab.model.DeviceUsingInfo;
import com.example.lab.model.User;
import com.example.lab.repository.DeviceUsingInfoRepository;

@Service
public class DeviceUsingService {

	@Autowired
	DeviceUsingInfoRepository deviceUsingInfoRepository;

	public List<DeviceUsingInfoResponseDto> getListUsingDeviceUser() {
		boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString()
				.equals("[ROLE_ADMIN]");
		if (!isAdmin) {
			User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return deviceUsingInfoRepository.getUsingDevicesByUser(u.getId());
		} else {
			return deviceUsingInfoRepository.getUsingDevicesByUser(null);
		}
	}

	public List<DeviceUsingInfoResponseDto> getListRegisterByDevice(Long deviceId) {
		return deviceUsingInfoRepository.getUsingDevicesByDevice(deviceId,DeviceUsingRegister.Inprogress.getVal());
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
		List<DeviceUsingInfo> devices = deviceUsingInfoRepository.getBusyDevices(info.getDeviceId(), info.getStart(),
				info.getEnd(), DeviceUsingRegister.Inprogress.getVal());
		return devices.isEmpty();
	}

	public DeviceUsingInfo cancelUsingDevice(Long id) {
		Optional<DeviceUsingInfo> opt = deviceUsingInfoRepository.findById(id);
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ADMIN]");
		if (!opt.isEmpty()) {
			DeviceUsingInfo device = opt.get();
			if(!u.getId().equals(device.getUserId())&&!isAdmin)
				return null;
			device.setRegisterStatus(DeviceUsingRegister.Cancel.getVal());
			device.setUpdateAt(LocalDateTime.now());
			deviceUsingInfoRepository.save(device);
			return device;
		}
		return null;
	}

	public DeviceUsingInfo doneUsingDevice(Long id) {
		Optional<DeviceUsingInfo> opt = deviceUsingInfoRepository.findById(id);
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ADMIN]");
		if (!opt.isEmpty()) {
			DeviceUsingInfo device = opt.get();
			if(!u.getId().equals(device.getUserId())&&!isAdmin)
				return null;
			device.setRegisterStatus(DeviceUsingRegister.Done.getVal());
			device.setUpdateAt(LocalDateTime.now());
			deviceUsingInfoRepository.save(device);
			return device;
		}
		return null;
	}
}
