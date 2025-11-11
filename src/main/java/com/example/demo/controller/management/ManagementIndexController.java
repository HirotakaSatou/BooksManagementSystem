package com.example.demo.controller.management;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.SearchForm;
import com.example.demo.service.LoginUserDetails;

/**
 * 管理トップページコントローラ
 */

@Controller
@RequestMapping("/management")
public class ManagementIndexController {

	// 管理トップページ - 画面表示
	@GetMapping
	public String index(@ModelAttribute SearchForm seForm, @AuthenticationPrincipal LoginUserDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getLastName() + " " + userDetails.getFirstName());
		model.addAttribute("seForm", seForm);
		return "management/index";
	}
}
