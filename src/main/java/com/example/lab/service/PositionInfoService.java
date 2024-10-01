package com.example.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab.dto.PositionInfoDto;
import com.example.lab.dto.request.PositionRegisterRequestDto;
import com.example.lab.model.PositionInfo;
import com.example.lab.repository.PositionInfoRepository;

@Service
public class PositionInfoService {

	@Autowired
	private PositionInfoRepository positionInfoRepository;

	public List<PositionInfoDto> getAllMasterData() {
		return positionInfoRepository.findAll().stream()
				.map(item -> new PositionInfoDto(item.getId(), item.getPositionInfo())).toList();
	}

	public void add(PositionRegisterRequestDto dto) throws Exception {
		PositionInfo info = new PositionInfo(dto);
		if (!positionInfoRepository.findByPositionInfo(info.getPositionInfo()).isEmpty())
			throw new Exception();
		positionInfoRepository.save(info);
	}

	public void deleteById(Long id) {
		positionInfoRepository.deleteById(id);
	}

}
