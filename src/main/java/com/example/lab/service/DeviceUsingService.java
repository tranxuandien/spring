package com.example.lab.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.lab.common.utils.RoleUtils;
import com.example.lab.dto.request.DeviceUsingRequestDto;
import com.example.lab.dto.request.ReportUsingDeviceStatusDto;
import com.example.lab.dto.response.DeviceUsingInfoResponseDto;
import com.example.lab.enums.DeviceUsingRegister;
import com.example.lab.model.DeviceInfo;
import com.example.lab.model.DeviceUsingInfo;
import com.example.lab.model.DeviceUsingUsers;
import com.example.lab.model.User;
import com.example.lab.repository.DeviceInfoRepository;
import com.example.lab.repository.DeviceUsingInfoRepository;
import com.example.lab.repository.DeviceUsingUsersRepository;

@Service
public class DeviceUsingService {

	@Autowired
	DeviceUsingInfoRepository deviceUsingInfoRepository;

	@Autowired
	DeviceInfoRepository deviceInfoRepository;

	@Autowired
	DeviceUsingUsersRepository deviceUsingUsersRepository;
	
	public List<DeviceUsingInfoResponseDto> getListUsingDeviceUser() {
		if (!RoleUtils.hasRoleBuddy()) {
			User u = RoleUtils.getCurrentUser();
			return deviceUsingInfoRepository.getUsingDevicesByUser(u.getId());
		} else {
			return deviceUsingInfoRepository.getUsingDevicesByUser(null);
		}
	}

	public List<DeviceUsingInfoResponseDto> getListRegisterByDevice(Long deviceId) {
		return deviceUsingInfoRepository.getUsingDevicesByDevice(deviceId, DeviceUsingRegister.Inprogress.getVal());
	}

	public DeviceUsingInfo use(DeviceUsingRequestDto dto) {
		DeviceUsingInfo saveObj = new DeviceUsingInfo(dto);
		Boolean isAvailable = checkBusyDevice(saveObj);
		if (isAvailable) {
			DeviceUsingInfo info = deviceUsingInfoRepository.save(saveObj);
			List<DeviceUsingUsers> users = dto.getUser().stream().map(item -> new DeviceUsingUsers(item, info.getId()))
					.toList();
			deviceUsingUsersRepository.saveAll(users);
			return info;
		} else
			return null;
	}

	private Boolean checkBusyDevice(DeviceUsingInfo info) {
		List<DeviceUsingInfo> devices = deviceUsingInfoRepository.getBusyDevices(info.getDeviceId(), info.getStart(),
				info.getEnd(), DeviceUsingRegister.Inprogress.getVal());
		return devices.isEmpty();
	}

	public DeviceUsingInfo cancelUsingDevice(Long id) {
		Optional<DeviceUsingInfo> opt = deviceUsingInfoRepository.findById(id);
		User u = RoleUtils.getCurrentUser();
		if (!opt.isEmpty()) {
			DeviceUsingInfo device = opt.get();
			if (!u.getId().equals(device.getUserId()) && !RoleUtils.hasRoleBuddy())
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
		User u = RoleUtils.getCurrentUser();
		if (!opt.isEmpty()) {
			DeviceUsingInfo device = opt.get();
			if (!u.getId().equals(device.getUserId()) && !RoleUtils.hasRoleBuddy())
				return null;
			device.setRegisterStatus(DeviceUsingRegister.Done.getVal());
			device.setUpdateAt(LocalDateTime.now());
			deviceUsingInfoRepository.save(device);
			return device;
		}
		return null;
	}

	public DeviceUsingInfo reportUsingDevice(ReportUsingDeviceStatusDto dto) {
		Optional<DeviceUsingInfo> opt = deviceUsingInfoRepository.findById(dto.getId());
		if (!opt.isEmpty()) {
			DeviceUsingInfo deviceUsing = opt.get();
			deviceUsing.setDeviceStatus(false);
			deviceUsing.setRegisterStatus(DeviceUsingRegister.Cancel.getVal());
			deviceUsing.setUpdateAt(LocalDateTime.now());
			deviceUsingInfoRepository.save(deviceUsing);

			DeviceInfo device = deviceInfoRepository.findById(deviceUsing.getDeviceId()).get();
			device.setDeviceStatus(false);
			device.setOtherInfo(dto.getDeviceStatusDetail());
			device.setUpdateAt(LocalDateTime.now());
			deviceInfoRepository.save(device);
			return deviceUsing;
		}
		return null;
	}
}
