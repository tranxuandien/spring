package com.example.lab.controller.user;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.lab.common.report.BarCodePDFExporter;
import com.example.lab.dto.SearchChemicalDto;
import com.example.lab.model.ChemicalInfo;
import com.example.lab.service.ChemicalInfoService;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

@Controller
public class UserController {

	@Autowired
	private ChemicalInfoService chemicalInfoService;

//	@RequestMapping("/login")
//    public String login(
//            @RequestParam(value = "error", required = false) String error,
//            @RequestParam(value = "logout", required = false) String logout,
//            Model model) {
//
//        if (error != null) {
//            model.addAttribute("errorMsg", "Tên đăng nhập hoặc mật khẩu không đúng.");
//        }
//
//        if (logout != null) {
//            model.addAttribute("logoutMsg", "Bạn đã đăng xuất thành công.");
//        }
//
//        return "user/login";  
//    }

	@GetMapping("/home")
	public String home(Model model) {
		return "user/home";
	}

	@GetMapping("/chemicals/export/pdf")
	public void exportToPDF(HttpServletResponse response)
			throws DocumentException, IOException, OutputException, BarcodeException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<ChemicalInfo> listChemical = chemicalInfoService.getListChemicalInfo(new SearchChemicalDto());

		BarCodePDFExporter exporter = new BarCodePDFExporter(listChemical);
		exporter.export(response);

	}
}
