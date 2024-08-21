package com.example.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab.dto.UserInfoDto;
import com.example.lab.repository.UserInfoRepository;

@Service
public class UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	public List<UserInfoDto> getAllMasterData() {
		return userInfoRepository.findAll().stream().map(item -> new UserInfoDto(item.getId(), item.getName()))
				.toList();
	}

}
