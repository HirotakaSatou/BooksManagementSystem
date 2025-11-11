package com.example.demo.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ログインコントローラ
 */

@Controller
@RequestMapping("/login")
public class LoginController {

	// ログイン - 画面表示
	@GetMapping
	public String login() {
		return "login/login";
	}
}
