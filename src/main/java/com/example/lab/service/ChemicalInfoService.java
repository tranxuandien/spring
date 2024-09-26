package com.example.lab.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.lab.common.report.BarCodePDFExporter;
import com.example.lab.dto.ChemicalDto;
import com.example.lab.dto.ChemicalUsingDto;
import com.example.lab.dto.masterdata.ChemicalMasterDataDto;
import com.example.lab.dto.request.ChemicalImportRequestDto;
import com.example.lab.dto.request.ChemicalInfoRequestDto;
import com.example.lab.dto.request.SearchChemicalInfoRequestDto;
import com.example.lab.dto.response.ChemicalInfoResponseDto;
import com.example.lab.enums.ImpExp;
import com.example.lab.model.Brand;
import com.example.lab.model.ChemicalImpExp;
import com.example.lab.model.ChemicalInfo;
import com.example.lab.model.ChemicalInventory;
import com.example.lab.model.ChemicalLotInfo;
import com.example.lab.model.PositionInfo;
import com.example.lab.model.User;
import com.example.lab.model.UserInfo;
import com.example.lab.repository.BrandRepository;
import com.example.lab.repository.ChemicalImpExpRepository;
import com.example.lab.repository.ChemicalInfoRepository;
import com.example.lab.repository.ChemicalInventoryRepository;
import com.example.lab.repository.ChemicalLotInfoRepository;
import com.example.lab.repository.PositionInfoRepository;
import com.example.lab.repository.UserInfoRepository;

import jakarta.transaction.Transactional;

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

	@Autowired
	private ChemicalLotInfoRepository chemicalLotInfoRepository;

	public List<ChemicalMasterDataDto> getLstMaster() {
		return chemicalInfoRepository.getLstMaster();
	}

//	public List<ChemicalInfoDto> getListChemicalInfo(SearchChemicalDto searchDto) {
//		if (searchDto.getChemical() == null) {
//			searchDto.setChemical(new ChemicalDto());
//		}
//		return chemicalInfoRepository.findAll(searchDto.getChemical().getId(), searchDto.getChemicalType(),
//				searchDto.getChemical().getChemicalName(), searchDto.getRange1(), searchDto.getRange2(),searchDto.getBrand(),searchDto.getChemicalClass(),searchDto.getPosition());
//	}
//	
	public List<ChemicalInfoResponseDto> getListChemicalInfo(SearchChemicalInfoRequestDto searchDto) {
		if (searchDto.getChemical() == null) {
			searchDto.setChemical(new ChemicalDto());
		}
		List<ChemicalInfo> lst = chemicalInfoRepository.findAll(searchDto.getChemical().getChemicalName(),
				searchDto.getChemicalType(), searchDto.getBrand(), searchDto.getChemicalClass());
		List<ChemicalInfoResponseDto> result = lst.stream().map(item -> new ChemicalInfoResponseDto(item)).toList();
		return result;
	}

	@Transactional
	public ChemicalInfo addChemical(ChemicalInfoRequestDto dto) {
		ChemicalInfo chemicalInfo = new ChemicalInfo(dto);

		Optional<Brand> brand = brandRepository.findById(Long.valueOf(dto.getBrand()));
		Optional<UserInfo> user = userInfoRepository.findByUsername(dto.getRegisterUser());
		if (brand.isEmpty() || user.isEmpty()) {
			return null;
		}
		chemicalInfo.setBrand(brand.get());
		chemicalInfo.setRegisterUser(user.get());
		ChemicalInfo chemical = chemicalInfoRepository.save(chemicalInfo);// save info
		return chemical;
	}

	public ChemicalInfoRequestDto getById(Long id) {
		ChemicalInfo info = chemicalInfoRepository.getById(id);
		return info == null ? null : new ChemicalInfoRequestDto(info);
	}

	public void deleteByCode(String code) {
		ChemicalInfo chemical = chemicalInfoRepository.findByCodeWithoutInventory(code);
		if (chemical != null)
			chemical.setIsDelete("1");
		chemicalInfoRepository.save(chemical);
		return;
	}

	public boolean checkDuplicate(String code) {
		return chemicalInfoRepository.findByCodeWithoutInventory(code) != null;
	}

	public String[] addPrintedChemicalLots(Integer chemicalId, Integer number) {
		String[] result = new String[number];
		List<ChemicalLotInfo> addLst = new ArrayList<ChemicalLotInfo>();
		Integer maxLot = chemicalLotInfoRepository.getMaxLot().isEmpty() ? 0
				: chemicalLotInfoRepository.getMaxLot().get();

		for (int i = 1; i <= number; i++) {
			result[i - 1] = String.valueOf(i + maxLot);
			addLst.add(new ChemicalLotInfo(null, chemicalId,
					"0".repeat(BarCodePDFExporter.CHEMICAL_LOT_LENGTH - String.valueOf(i + maxLot).length())
							+ String.valueOf(i + maxLot),
					false));
		}
		chemicalLotInfoRepository.saveAll(addLst);
		return result;
	}

	public Optional<ChemicalInfo> getChemicalFromBarcode(String barcode) {
		Long chemicalId = Long.valueOf(barcode.substring(0, BarCodePDFExporter.CHEMICAL_CODE_LENGTH));
		String lotCode = barcode.substring(BarCodePDFExporter.CHEMICAL_CODE_LENGTH,
				BarCodePDFExporter.CHEMICAL_CODE_LENGTH + BarCodePDFExporter.CHEMICAL_LOT_LENGTH);
		ChemicalLotInfo lot = chemicalLotInfoRepository.getChemicalLot(chemicalId, lotCode);
		if (lot == null) {
			return Optional.empty();
		}
		return chemicalInfoRepository.findById(chemicalId);
	}

	@Transactional
	public void registerChemical(ChemicalImportRequestDto requestDto) {
		Long chemicalId = Long.valueOf(requestDto.getBarcode().substring(0, BarCodePDFExporter.CHEMICAL_CODE_LENGTH));
		String lotCode = requestDto.getBarcode().substring(BarCodePDFExporter.CHEMICAL_CODE_LENGTH,
				BarCodePDFExporter.CHEMICAL_CODE_LENGTH + BarCodePDFExporter.CHEMICAL_LOT_LENGTH);
		ChemicalLotInfo lot = chemicalLotInfoRepository.getChemicalLot(chemicalId, lotCode);
		Optional<PositionInfo> position = positionInfoRepository.findById(requestDto.getPosition());
		if (lot == null || position.isEmpty()) {
			return;
		}

		// save chemical lot
		lot.setImport(true);
		lot.setUpdateAt(LocalDate.now());
		chemicalLotInfoRepository.save(lot);

		// save inventory
		ChemicalInventory inventory = new ChemicalInventory(null, chemicalId, requestDto.getManufactoryQuantity(),
				lot.getId(), requestDto.getChemicalStatus(), requestDto.getPurchaseSrc(), position.get(),
				requestDto.getExpiredDate());
		chemicalInventoryRepository.save(inventory);

		// add imp info
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ChemicalImpExp impexp = new ChemicalImpExp(null, ImpExp.Import.getVal(), requestDto.getManufactoryQuantity(),
				chemicalId, user.getId(), null, lot.getId());
		chemicalImpExpRepository.save(impexp);
	}

	public Optional<ChemicalInfo> getUsingChemicalFromBarcode(String barcode) {
		Long chemicalId = Long.valueOf(barcode.substring(0, BarCodePDFExporter.CHEMICAL_CODE_LENGTH));
		String lotCode = barcode.substring(BarCodePDFExporter.CHEMICAL_CODE_LENGTH,
				BarCodePDFExporter.CHEMICAL_CODE_LENGTH + BarCodePDFExporter.CHEMICAL_LOT_LENGTH);
		ChemicalLotInfo lot = chemicalLotInfoRepository.getUsingChemicalLot(chemicalId, lotCode);
		if (lot == null) {
			return Optional.empty();
		}
		return chemicalInfoRepository.findById(chemicalId);
	}

	@Transactional
	public void usingChemical(ChemicalInfoRequestDto info, ChemicalUsingDto updateDto) throws Throwable {
		String lotCode = updateDto.getBarcode().substring(BarCodePDFExporter.CHEMICAL_CODE_LENGTH,
				BarCodePDFExporter.CHEMICAL_CODE_LENGTH + BarCodePDFExporter.CHEMICAL_LOT_LENGTH);
		ChemicalInventory inventory = chemicalInventoryRepository.findByChemicalId(info.getId(), lotCode);
		if (inventory.getQuantity().subtract(updateDto.getQuantity()).compareTo(BigDecimal.ZERO) < 0)
			throw new Exception("Giá trị sử dụng nhiều hơn số lượng hóa chất còn lại");
		inventory.setQuantity(inventory.getQuantity().subtract(updateDto.getQuantity()));
		chemicalInventoryRepository.save(inventory);
		// add imp info
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ChemicalImpExp impexp = new ChemicalImpExp(null, ImpExp.Export.getVal(), updateDto.getQuantity(), info.getId(),
				null, user.getId(), inventory.getLotId());
		chemicalImpExpRepository.save(impexp);
	}

	public List<ChemicalInfo> getExistChemicalInfo(SearchChemicalInfoRequestDto searchDto) {
		if (searchDto.getChemical() == null) {
			searchDto.setChemical(new ChemicalDto());
		}
		List<ChemicalInfo> lst = chemicalInfoRepository.findExist(searchDto.getChemical().getChemicalName(),
				searchDto.getChemicalType(), searchDto.getBrand(), searchDto.getChemicalClass());
		return lst;
	}

	public ChemicalInfo save(ChemicalInfo chemical) {
		return chemicalInfoRepository.save(chemical);
	}
}
