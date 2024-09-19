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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab.common.message.CommonMessage;
import com.example.lab.common.message.ErrorMessage;
import com.example.lab.common.report.BarCodePDFExporter;
import com.example.lab.dto.ChemicalInfoDto;
import com.example.lab.dto.ChemicalUsingDto;
import com.example.lab.dto.SearchChemicalDto;
import com.example.lab.dto.masterdata.ChemicalMasterDataDto;
import com.example.lab.dto.response.CommonResponseEntity;
import com.example.lab.model.ChemicalInfo;
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

	@Autowired
	private ChemicalInfoService chemicalInfoService;

//	@Autowired
//	private BrandService brandService;
//
//	@Autowired
//	private UserInfoService userInfoService;
//
//	@Autowired
//	private PositionInfoService positionInfoService;

	@GetMapping("/chemical/list")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> getListChemicalRest() {
		List<ChemicalInfoDto> dto = chemicalInfoService.getListChemicalInfo(new SearchChemicalDto());
		for (ChemicalInfoDto chemicalInfoDto : dto) {
			chemicalInfoDto.updateImpExpInfo();
		}
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/chemical/list/master")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> getListChemical() {
		List<ChemicalMasterDataDto> dto = chemicalInfoService.getLstMaster();
		return ResponseEntity.ok(dto);
	}

	@PostMapping("/chemical/list")
	public CommonResponseEntity getListChemical(@RequestBody SearchChemicalDto searchChemicalDto) {
		searchChemicalDto.setRangeSearch();
		List<ChemicalInfoDto> dto = chemicalInfoService.getListChemicalInfo(searchChemicalDto);
		for (ChemicalInfoDto chemicalInfoDto : dto) {
			chemicalInfoDto.updateImpExpInfo();
		}
		return CommonResponseEntity.builder().data(dto).build();
	}

	// use
	@GetMapping("/admin/chemical/register")
	public ResponseEntity<?> addChemical(Model model, @PathParam("barcode") String barcode) {
		if (barcode.isEmpty())
			return ResponseEntity.noContent().build();
		Optional<ChemicalInfo> opt = chemicalInfoService.getChemicalFromBarcode(barcode);
		if (opt.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(new ChemicalInfoDto(opt.get()));
//		getMasterData(model);
//		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		ChemicalInfoDto form = new ChemicalInfoDto();
//		form.setRegisterUser(user.getUserId().toString());
//		model.addAttribute("chemical", form);
//
//		return "chemical/chemicalRegister";
	}

	// import chemical
	@GetMapping("/admin/chemical/import")
	public CommonResponseEntity importChemical(@PathParam("barcode") String barcode) {
		if (barcode.isEmpty())
			return CommonResponseEntity.builder().errorMessage(ErrorMessage.BARCODE_IS_EMPTY_MESSAGE).build();
		Optional<ChemicalInfo> opt = chemicalInfoService.getChemicalFromBarcode(barcode);
		if (opt.isEmpty()) {
			return CommonResponseEntity.builder().errorMessage(ErrorMessage.IMPORTED_CHEMICAL_MESSAGE).build();
		}
		chemicalInfoService.registerChemical(barcode);
		return CommonResponseEntity.builder().data(new ChemicalInfoDto(opt.get())).build();
	}

	@PostMapping("/admin/chemical/add")
	public ResponseEntity<?> addChemical(@RequestBody ChemicalInfoDto chemical) {
		ChemicalInfo addChemical = chemicalInfoService.addChemical(chemical);
		if (addChemical.equals(null))
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(CommonResponseEntity.builder().errorMessage(ErrorMessage.CANNOT_ADD_CHEMICAL).build());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(CommonResponseEntity.builder().message(CommonMessage.REGISTED_CHEMICAL_MESSAGE).build());
	}

	@GetMapping("/admin/chemical/codeprint")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> exportToPDF(@PathParam("chemicalId") Integer chemicalId, @PathParam("chemicalName") String chemicalName,
			@PathParam("number") Integer number, HttpServletResponse response)
			throws DocumentException, IOException, OutputException, BarcodeException {
		if (number > 1000)
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

	@GetMapping("/chemical/use")
	public ResponseEntity<?> usingChemical(@PathParam("barcode") String barcode) {
//		if (barcode.isEmpty())
//			return ResponseEntity.noContent().build();
//		Optional<ChemicalInfo> opt = chemicalInfoService.getChemicalFromBarcode(barcode);
//		if (opt.isEmpty()) {
//			return ResponseEntity.noContent().build();
//		}
//		chemicalInfoService.registerChemical(barcode);
		return ResponseEntity.ok(null);
	}

	@PostMapping("/chemical/use")
	public String usingChemical(@ModelAttribute("chemical") @Valid ChemicalUsingDto updateDto, BindingResult result,
			Model model) throws Throwable {
//		ChemicalInfoDto info = chemicalInfoService.getById(updateDto.getId());
//		if (info == null)
//			result.failOnError(null);
//		else
//			chemicalInfoService.usingChemical(info, updateDto);
//		return this.getListChemical(model);
		return null;
	}

	@GetMapping("/chemical/delete/{code}")
	public String deleteChemical(@PathVariable(value = "code") String code, Model model) {
		chemicalInfoService.deleteByCode(code);
//		model.addAttribute("chemical", dto);
//		return this.getListChemical(model);
		return null;
	}
}
