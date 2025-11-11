package com.example.demo.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.SearchForm;
import com.example.demo.service.BooksService;
import com.example.demo.service.LoginUserDetails;

@Controller
@RequestMapping("/management/books")
public class BooksController {
		
	@Autowired
	BooksService booksService;
		
	@GetMapping
	String index(@ModelAttribute SearchForm seForm, Model model, @AuthenticationPrincipal LoginUserDetails userDetails) {
				
		model.addAttribute("user", userDetails.getLastName() + " " + userDetails.getFirstName());
		model.addAttribute("seForm", seForm);
		return "management/books";
	}	
}
