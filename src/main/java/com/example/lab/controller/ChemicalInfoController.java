package com.example.lab.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab.common.message.CommonMessage;
import com.example.lab.common.message.ErrorMessage;
import com.example.lab.common.report.BarCodePDFExporter;
import com.example.lab.dto.ChemicalDto;
import com.example.lab.dto.ChemicalUsingDto;
import com.example.lab.dto.masterdata.ChemicalMasterDataDto;
import com.example.lab.dto.request.ChemicalImportRequestDto;
import com.example.lab.dto.request.ChemicalInfoRequestDto;
import com.example.lab.dto.request.SearchChemicalInfoRequestDto;
import com.example.lab.dto.response.ChemicalInfoResponseDto;
import com.example.lab.dto.response.ChemicalUsingResponseDto;
import com.example.lab.dto.response.CommonResponseEntity;
import com.example.lab.model.Brand;
import com.example.lab.model.ChemicalInfo;
import com.example.lab.service.BrandService;
import com.example.lab.service.ChemicalInfoService;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

@RestController
@RequestMapping("api/v1")
public class ChemicalInfoController {

	public static final Integer PRINT_LIMIT_NUMBER = 10;

	@Autowired
	private ChemicalInfoService chemicalInfoService;

	@Autowired
	private BrandService brandService;

	@GetMapping("/chemical/list")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> getListChemicalRest() {
		List<ChemicalInfoResponseDto> dtos = chemicalInfoService
				.getListChemicalInfo(new SearchChemicalInfoRequestDto());
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/chemical/list/master")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> getListChemical() {
		List<ChemicalMasterDataDto> dto = chemicalInfoService.getLstMaster();
		return ResponseEntity.ok(dto);
	}

	@PostMapping("/chemical/list")
	public CommonResponseEntity getListChemical(@RequestBody SearchChemicalInfoRequestDto searchChemicalDto) {
		List<ChemicalInfoResponseDto> dtos = chemicalInfoService.getListChemicalInfo(searchChemicalDto);
		return CommonResponseEntity.builder().data(dtos).build();
	}

	@PostMapping("/admin/chemical/add")
	public ResponseEntity<?> addChemical(@RequestBody @Valid ChemicalInfoRequestDto chemical) {
		SearchChemicalInfoRequestDto searchDto = new SearchChemicalInfoRequestDto(
				new ChemicalDto(null, chemical.getName()), chemical.getBrand(), chemical.getChemicalType(),
				chemical.getChemicalClass());
		List<ChemicalInfo> infos = chemicalInfoService.getExistChemicalInfo(searchDto);
		if (!infos.isEmpty())
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
					CommonResponseEntity.builder().errorMessage(ErrorMessage.CHEMICAL_CHEMICAL_DUPLICATED).build());
		ChemicalInfo addChemical = chemicalInfoService.addChemical(chemical);
		if (addChemical.equals(null))
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
					CommonResponseEntity.builder().errorMessage(ErrorMessage.CHEMICAL_CANNOT_ADD_CHEMICAL).build());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(CommonResponseEntity.builder().message(CommonMessage.REGISTED_CHEMICAL_MESSAGE).build());
	}

	@GetMapping("/admin/chemical/find")
	public ResponseEntity<?> updateChemical(@PathParam("id") Long id) {
		Optional<ChemicalInfo> chemicalInfo = chemicalInfoService.findById(id);
		if (chemicalInfo.isEmpty())
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(CommonResponseEntity.builder()
					.errorMessage(ErrorMessage.CHEMICAL_CANNOT_GET_CHEMICAL_INFO).build());
		ChemicalInfoResponseDto dto = new ChemicalInfoResponseDto(chemicalInfo.get(), null);
		dto.setBrand(chemicalInfo.get().getBrand().getId().toString());
		return ResponseEntity.status(HttpStatus.OK).body(CommonResponseEntity.builder().data(dto).build());
	}

	@PutMapping("/admin/chemical/update")
	public ResponseEntity<?> updateChemical(@RequestBody @Valid ChemicalInfoRequestDto chemical) {
		Optional<ChemicalInfo> chemicalInfo = chemicalInfoService.findById(chemical.getId());
		if (chemicalInfo.isEmpty())
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.CHEMICAL_CANNOT_UPDATE).build());
		ChemicalInfo obj = chemicalInfo.get();
		Optional<Brand> brand = brandService.findByid(Long.valueOf(chemical.getBrand()));
		obj.update(chemical);
		obj.setBrand(brand.get());
		ChemicalInfo updateChemical = chemicalInfoService.save(obj);
		if (updateChemical.equals(null))
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.CHEMICAL_CANNOT_UPDATE).build());
		return ResponseEntity.status(HttpStatus.OK)
				.body(CommonResponseEntity.builder().message(CommonMessage.CHEMICAL_UPDATED).build());
	}

	// get info scan barcode
	@GetMapping("/admin/chemical/register")
	public ResponseEntity<?> getChemical(Model model, @PathParam("barcode") String barcode) {
		if (barcode.isEmpty())
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(CommonResponseEntity.builder()
					.errorMessage(ErrorMessage.CHEMICAL_BARCODE_IS_EMPTY_MESSAGE).build());
		ChemicalInfoResponseDto result = chemicalInfoService.getChemicalFromBarcode(barcode);
		if (result == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(CommonResponseEntity.builder()
					.errorMessage(ErrorMessage.CHEMICAL_CANNOT_GET_CHEMICAL_INFO).build());
		}
		return ResponseEntity.ok(result);
	}

	// do import chemical
	@PostMapping("/admin/chemical/import")
	public ResponseEntity<?> importChemical(@RequestBody @Valid ChemicalImportRequestDto requestDto) {
		if (requestDto.getBarcode().isEmpty())
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(CommonResponseEntity.builder()
					.errorMessage(ErrorMessage.CHEMICAL_BARCODE_IS_EMPTY_MESSAGE).build());
		ChemicalInfoResponseDto chemical = chemicalInfoService.getChemicalFromBarcode(requestDto.getBarcode());
		if (chemical == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(CommonResponseEntity.builder()
					.errorMessage(ErrorMessage.CHEMICAL_IMPORTED_CHEMICAL_MESSAGE).build());
		}
		try {
			chemicalInfoService.registerChemical(requestDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(CommonResponseEntity.builder()
					.errorMessage(ErrorMessage.CHEMICAL_CANNOT_IMPORT_CHEMICAL_MESSAGE).build());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(CommonResponseEntity.builder().message(CommonMessage.IMPORTED_CHEMICAL_MESSAGE).build());
	}

	@GetMapping("/admin/chemical/codeprint")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> exportToPDF(@PathParam("chemicalId") Integer chemicalId,
			@PathParam("chemicalName") String chemicalName, @PathParam("number") Integer number,
			HttpServletResponse response) throws DocumentException, IOException, OutputException, BarcodeException {
		if (number > PRINT_LIMIT_NUMBER)
			return ResponseEntity.noContent().build();
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		String[] printLst = chemicalInfoService.addPrintedChemicalLots(chemicalId, number);
		BarCodePDFExporter exporter = new BarCodePDFExporter(chemicalId, number, printLst, chemicalName);
		exporter.export(response);
		response.setStatus(HttpStatus.NO_CONTENT.value());
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/admin/chemical/code/reprint")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> rePrintChemicalBarcode(@PathParam("barcode") String barcode,
			@PathParam("chemicalName") String chemicalName, HttpServletResponse response)
			throws DocumentException, IOException, OutputException, BarcodeException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		BarCodePDFExporter exporter = new BarCodePDFExporter(barcode, chemicalName);
		exporter.exportOne(response);
		response.setStatus(HttpStatus.NO_CONTENT.value());
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/chemical/using/get")
	public ResponseEntity<?> getUsingChemical(@PathParam("barcode") String barcode) {
		if (barcode.isEmpty())
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(CommonResponseEntity.builder()
					.errorMessage(ErrorMessage.CHEMICAL_BARCODE_IS_EMPTY_MESSAGE).build());
		ChemicalUsingResponseDto result = chemicalInfoService.getUsingChemicalFromBarcode(barcode);
		if (result == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(CommonResponseEntity.builder()
					.errorMessage(ErrorMessage.CHEMICAL_CANNOT_GET_CHEMICAL_INFO).build());
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("/chemical/using")
	public ResponseEntity<?> usingChemical(@RequestBody @Valid ChemicalUsingDto updateDto) throws Throwable {
		ChemicalInfoRequestDto info = chemicalInfoService
				.getById(Long.valueOf(updateDto.getBarcode().substring(0, BarCodePDFExporter.CHEMICAL_CODE_LENGTH)));
		if (info == null)
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(CommonResponseEntity.builder()
					.errorMessage(ErrorMessage.CHEMICAL_CANNOT_GET_CHEMICAL_INFO).build());
		else {
			try {
				chemicalInfoService.usingChemical(info, updateDto);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
						CommonResponseEntity.builder().errorMessage(ErrorMessage.CHEMICAL_CANNOT_USE_CHEMICAL).build());
			}
		}
		return ResponseEntity.ok(CommonResponseEntity.builder()
				.message("Đã đăng ký sử dụng " + updateDto.getQuantity() + "(g/ml) hóa chất" + info.getName()).build());
	}

	@DeleteMapping("/chemical/delete/{code}")
	public ResponseEntity<?> deleteChemical(@PathVariable(value = "code") String code) {
		if (chemicalInfoService.deleteByCode(code) == null)
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
					CommonResponseEntity.builder().errorMessage(ErrorMessage.CHEMICAL_INFO_CANNOT_DELETED).build());
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(CommonResponseEntity.builder().message(CommonMessage.CHEMICAL_INFO_DELETED).build());
	}
}
