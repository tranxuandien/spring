package com.example.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.lab.dto.ChemicalInfoDto;
import com.example.lab.dto.SearchChemicalDto;
import com.example.lab.model.security.CustomUser;
import com.example.lab.service.BrandService;
import com.example.lab.service.ChemicalInfoService;
import com.example.lab.service.PositionInfoService;
import com.example.lab.service.UserInfoService;

import jakarta.validation.Valid;

@Controller
public class ChemicalInfoController {

	@Autowired
	private ChemicalInfoService chemicalInfoService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private PositionInfoService positionInfoService;

	@GetMapping("/chemical/list")
	public String getListChemical(Model model) {
		List<ChemicalInfoDto> dto = chemicalInfoService.getListChemicalInfo(new SearchChemicalDto()).stream()
				.map(item -> new ChemicalInfoDto(item)).toList();

		model.addAttribute("searchDto", new SearchChemicalDto());
		model.addAttribute("chemicals", dto);
		return "chemical/chemicalInfo";
	}

	@PostMapping("/chemical/list")
	public String getListChemical(@ModelAttribute("searchDto") SearchChemicalDto searchChemicalDto,Model model) {

		List<ChemicalInfoDto> dto = chemicalInfoService.getListChemicalInfo(searchChemicalDto).stream()
				.map(item -> new ChemicalInfoDto(item)).toList();

//		model.addAttribute("searchDto", new SearchChemicalDto());
		model.addAttribute("chemicals", dto);
		return "chemical/chemicalInfo";
	}
	
	@GetMapping("/chemical/register")
	public String addChemical(Model model) {
		getMasterData(model);
		CustomUser user = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ChemicalInfoDto form =  new ChemicalInfoDto();
		form.setRegisterUser(user.getUserId().toString());
		model.addAttribute("chemical", form);
		
		return "chemical/chemicalRegister";
	}

	@PostMapping("/chemical/add")
	public String addChemical(@ModelAttribute("chemical") @Valid ChemicalInfoDto chemical, BindingResult result, Model model) {
		if (result.hasErrors()) {
			getMasterData(model);
			return "chemical/chemicalRegister";
		}
		chemicalInfoService.addChemical(chemical);
//		List<ChemicalInfoDto> dto = chemicalInfoService.getListChemicalInfo(null).stream()
//				.map(item -> new ChemicalInfoDto(item)).toList();
		
//		model.addAttribute("chemicals", dto);
		return "chemical/chemicalInfo";
	}
	
	private Model getMasterData(Model model)
	{
		model.addAttribute("users", userInfoService.getAllMasterData());
		model.addAttribute("brands", brandService.getAllMasterData());
		model.addAttribute("positions", positionInfoService.getAllMasterData());
		return model;
	}
}