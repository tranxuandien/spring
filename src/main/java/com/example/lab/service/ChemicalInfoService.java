package com.example.lab.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.lab.dto.ChemicalInfoDto;
import com.example.lab.dto.ChemicalUsingDto;
import com.example.lab.dto.SearchChemicalDto;
import com.example.lab.enums.ImpExp;
import com.example.lab.model.Brand;
import com.example.lab.model.ChemicalImpExp;
import com.example.lab.model.ChemicalInfo;
import com.example.lab.model.ChemicalInventory;
import com.example.lab.model.PositionInfo;
import com.example.lab.model.UserInfo;
import com.example.lab.model.security.CustomUser;
import com.example.lab.repository.BrandRepository;
import com.example.lab.repository.ChemicalImpExpRepository;
import com.example.lab.repository.ChemicalInfoRepository;
import com.example.lab.repository.ChemicalInventoryRepository;
import com.example.lab.repository.PositionInfoRepository;
import com.example.lab.repository.UserInfoRepository;

@Service
public class ChemicalInfoService {

	@Autowired
	private ChemicalInfoRepository chemicalInfoRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private PositionInfoRepository positionInfoRepository;

	@Autowired
	private ChemicalImpExpRepository chemicalImpExpRepository;

	@Autowired
	private ChemicalInventoryRepository chemicalInventoryRepository;

//	public List<Object[]>  getListChemicalInfo(ChemicalInfoParam chemicalInfoParam) {
//		return chemicalInfoRepository.findAll(chemicalInfoParam);
//	}
//	
	public List<ChemicalInfoDto> getListChemicalInfo(SearchChemicalDto searchDto) {
		return chemicalInfoRepository.findAll(searchDto.getCode(), searchDto.getChemicalType(), searchDto.getName(),searchDto.getRange1(),searchDto.getRange2());
	}

	public ChemicalInfo addChemical(ChemicalInfoDto dto) {
		ChemicalInfo chemicalInfo = new ChemicalInfo(dto);

		Optional<Brand> brand = brandRepository.findById(Long.valueOf(dto.getBrand()));
		Optional<UserInfo> user = userInfoRepository.findById(Long.valueOf(dto.getRegisterUser()));
		Optional<PositionInfo> pos = positionInfoRepository.findById(Long.valueOf(dto.getPosition()));

		if (brand.isEmpty() || user.isEmpty() || pos.isEmpty()) {
			return null;
		}
		chemicalInfo.setPosition(pos.get());
		chemicalInfo.setBrand(brand.get());
//		chemicalInfo.setRegisterUser(user.get());
		ChemicalInfo chemical = chemicalInfoRepository.save(chemicalInfo);// save info

		// save inventory
		ChemicalInventory inventory = new ChemicalInventory(null, chemical.getId(), dto.getQuantity());
		chemicalInventoryRepository.save(inventory);

		// add imp info
		ChemicalImpExp impexp = new ChemicalImpExp(null, dto.getImpExpInfo(), dto.getQuantity(), chemical.getId(),
				Long.parseLong(dto.getRegisterUser()), null);
		chemicalImpExpRepository.save(impexp);
		return chemicalInfo;
	}

	public ChemicalInfoDto getByCode(String code) {
		return new ChemicalInfoDto(chemicalInfoRepository.findByCode(code));
	}

	public void usingChemical(ChemicalInfoDto info, ChemicalUsingDto updateDto) throws Throwable {
		ChemicalInventory inventory = chemicalInventoryRepository.findByChemicalId(info.getId());
		if (inventory.getQuantity().subtract(updateDto.getQuantity()).compareTo(BigDecimal.ZERO) < 0)
			throw new Exception("Giá trị sử dụng nhiều hơn số lượng hóa chất còn lại");
		inventory.setQuantity(inventory.getQuantity().subtract(updateDto.getQuantity()));
		chemicalInventoryRepository.save(inventory);
		// add imp info
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ChemicalImpExp impexp = new ChemicalImpExp(null, ImpExp.Export.getVal(), updateDto.getQuantity(), info.getId(),
				null, user.getUserId());
		chemicalImpExpRepository.save(impexp);
	}

	public void deleteByCode(String code) {
		ChemicalInfo chemical = chemicalInfoRepository.findByCodeWithoutInventory(code);
		if (chemical != null)
			chemical.setIsDelete("1");
		chemicalInfoRepository.save(chemical);
		return;
	}

	public boolean checkDuplicate(String code) {
		return chemicalInfoRepository.findByCodeWithoutInventory(code)!=null;
	}

}
