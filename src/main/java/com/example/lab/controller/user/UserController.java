package com.example.lab.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.lab.service.ChemicalInfoService;

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

	@GetMapping("/login")
	String login() {
		return "user/login";
	}

	@GetMapping("/index")
	public String home(Model model) {
		return "user/index";
	}

}
