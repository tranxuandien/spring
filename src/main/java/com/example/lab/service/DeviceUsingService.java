package com.example.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.lab.dto.response.DeviceUsingInfoResponseDto;
import com.example.lab.model.User;
import com.example.lab.repository.DeviceUsingRepository;

@Service
public class DeviceUsingService {

	@Autowired
	DeviceUsingRepository deviceUsingRepository;

	public List<DeviceUsingInfoResponseDto> getListUsingDeviceUser() {
		boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString()
				.equals("[ROLE_ADMIN]");
		if (!isAdmin) {
			User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return deviceUsingRepository.getUsingDevicesByUser(u.getId());
		} else {
			return deviceUsingRepository.getUsingDevicesByUser(null);
		}
	}
}
