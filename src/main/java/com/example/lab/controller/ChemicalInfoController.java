package com.example.lab.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.text.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab.common.report.BarCodePDFExporter;
import com.example.lab.dto.ChemicalInfoDto;
import com.example.lab.dto.ChemicalUsingDto;
import com.example.lab.dto.SearchChemicalDto;
import com.example.lab.dto.masterdata.ChemicalMasterDataDto;
import com.example.lab.model.ChemicalInfo;
import com.example.lab.model.security.CustomUser;
import com.example.lab.service.BrandService;
import com.example.lab.service.ChemicalInfoService;
import com.example.lab.service.PositionInfoService;
import com.example.lab.service.UserInfoService;
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

	@Autowired
	private BrandService brandService;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private PositionInfoService positionInfoService;

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
	public String getListChemical(@ModelAttribute("searchDto") SearchChemicalDto searchChemicalDto, Model model) {
		searchChemicalDto.setRangeSearch();
		List<ChemicalInfoDto> dto = chemicalInfoService.getListChemicalInfo(searchChemicalDto);
		for (ChemicalInfoDto chemicalInfoDto : dto) {
			chemicalInfoDto.updateImpExpInfo();
		}
		model.addAttribute("chemicals", dto);
		return "chemical/chemicalInfo";
	}
	//use
	@GetMapping("/chemical/register")
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

	//TODO
	@GetMapping("/chemical/import")
	public ResponseEntity<?> importChemical(@PathParam("barcode") String barcode) {
		if (barcode.isEmpty())
			return ResponseEntity.noContent().build();
		Optional<ChemicalInfo> opt = chemicalInfoService.getChemicalFromBarcode(barcode);
		if (opt.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
//		chemicalInfoService.importChemical(barcode);
		return ResponseEntity.ok(new ChemicalInfoDto(opt.get()));
	}
	
	@PostMapping("/chemical/add")
	public BodyBuilder addChemical(@RequestBody ChemicalInfoDto chemical, BindingResult result,
			Model model) {
		
//		if (chemicalInfoService.checkDuplicate(chemical.getCode()))
//			result.addError(new FieldError("chemical", "code", "Code đã được đăng ký!"));
		
//		if (result.hasErrors()) {
//			return ResponseEntity.ok();
//		}
		ChemicalInfo addChemical = chemicalInfoService.addChemical(chemical);
		ResponseEntity.ok(addChemical);
		return ResponseEntity.status(HttpStatus.CREATED);
	}

	@GetMapping("/chemical/codeprint")
	public void exportToPDF(@PathParam("chemicalId") Integer chemicalId,@PathParam("chemicalName") String chemicalName, @PathParam("number") Integer number,
			HttpServletResponse response) throws DocumentException, IOException, OutputException, BarcodeException {
		if(number>1000) return;
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		String[] printLst = chemicalInfoService.addPrintedChemicalLots(chemicalId,number);
		BarCodePDFExporter exporter = new BarCodePDFExporter(chemicalId,number,printLst,chemicalName);
		exporter.export(response);
	}

	@GetMapping("/chemical/usingRegister/{code}")
	public String usingChemical(@PathVariable(value = "id") Long id, Model model) {
		ChemicalInfoDto dto = chemicalInfoService.getById(id);
		model.addAttribute("chemical", dto);
		return "chemical/chemicalUsingRegister";
	}

	@PostMapping("/chemical/use")
	public String usingChemical(@ModelAttribute("chemical") @Valid ChemicalUsingDto updateDto, BindingResult result,
			Model model) throws Throwable {
		ChemicalInfoDto info = chemicalInfoService.getById(updateDto.getId());
		if (info == null)
			result.failOnError(null);
		else
			chemicalInfoService.usingChemical(info, updateDto);
		return this.getListChemical(model);
	}

	@GetMapping("/chemical/delete/{code}")
	public String deleteChemical(@PathVariable(value = "code") String code, Model model) {
		chemicalInfoService.deleteByCode(code);
//		model.addAttribute("chemical", dto);
		return this.getListChemical(model);
	}

//	private Model getMasterData(Model model) {
//		model.addAttribute("users", userInfoService.getAllMasterData());
//		model.addAttribute("brands", brandService.getAllMasterData());
//		model.addAttribute("positions", positionInfoService.getAllMasterData());
//		return model;
//	}
}
