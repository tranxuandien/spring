package com.example.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab.dto.request.SearchImpExpRequestDto;
import com.example.lab.dto.request.UserDto;
import com.example.lab.dto.response.ChemicalImpExpChartResponseDto;
import com.example.lab.dto.response.ChemicalImpExpResponseDto;
import com.example.lab.dto.response.CommonResponseEntity;
import com.example.lab.model.User;
import com.example.lab.service.ChemicalImpExpService;

@RestController
@RequestMapping("api/v1")
public class ChemicalImpExpController {

	@Autowired
	private ChemicalImpExpService chemicalImpExpService;

	@PostMapping("/chemical/impexp/list")
	public CommonResponseEntity getListImpExpList(@RequestBody SearchImpExpRequestDto search) {
		boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ADMIN]");
		search.init();
		if (!isAdmin) {
			User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDto dto = new UserDto();
			dto.setId(u.getId());
			search.setUser(dto);
		}
		List<ChemicalImpExpResponseDto> dtos = chemicalImpExpService.getListImpExpDetail(search);
		return CommonResponseEntity.builder().data(dtos).build();
	}
	
	@PostMapping("/chemical/impexp/chart/list")
	public CommonResponseEntity getListImpExpChartList(@RequestBody SearchImpExpRequestDto search) {
		boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ADMIN]");
		search.init();
		if (!isAdmin) {
			User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDto dto = new UserDto();
			dto.setId(u.getId());
			search.setUser(dto);
		}
		List<ChemicalImpExpChartResponseDto> dtos = chemicalImpExpService.getListImpExpChart(search);
		return CommonResponseEntity.builder().data(dtos).build();
	}
}
