package com.example.demo.controller.auth;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.UsersForm;
import com.example.demo.service.UsersService;

/**
 * ユーザー新規登録コントローラ
 */

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	UsersService usersService;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@ModelAttribute
	public UsersForm setUpForm() {
		return new UsersForm();
	}
	
	// ユーザー新規登録 - 画面表示
	@GetMapping
	public String register() {
		return "login/register";
	}
	
	// ユーザー新規登録 - ユーザー登録処理
	@PostMapping
	public String insert(@Validated @ModelAttribute UsersForm usForm, BindingResult result, Model model) {
		Long datetime = System.currentTimeMillis();
		Timestamp time = new Timestamp(datetime);
		if (result.hasErrors()) {
			return "login/register";
		}
		
		// 新規ユーザーの登録
//		Users users = new Users(); // FormからEntityへ詰め替え
//		users.setLastName(usForm.getLastName());
//		users.setFirstName(usForm.getFirstName());
//		users.setEmail(usForm.getEmail());
//		users.setPassword(passwordEncoder.encode(usForm.getPassword()));
//		users.setRole(2);
//		users.setCreatedAt(time);
//		users.setUpdatedAt(time);
//		usersService.insertUser(users); // Entityに詰めたデータをDBに登録する
		
		usersService.insert(usForm.getLastName(), usForm.getFirstName(), usForm.getEmail(), passwordEncoder.encode(usForm.getPassword()), 2, time, time);
		
		model.addAttribute("complete", "ユーザー登録が完了しました");
		return "login/login";
	}
}
