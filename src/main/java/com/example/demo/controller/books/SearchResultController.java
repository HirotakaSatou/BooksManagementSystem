package com.example.demo.controller.books;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.BookNames;
import com.example.demo.entity.Genre;
import com.example.demo.form.SearchForm;
import com.example.demo.service.GenreService;
import com.example.demo.service.LoginUserDetails;
import com.example.demo.service.SearchService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 検索結果コントローラ
 */

@Controller
@RequestMapping("/book")
public class SearchResultController {

	@Autowired
	SearchService searchService;
	@Autowired
    GenreService genreService;
	
	// 検索結果 - 画面表示
	@GetMapping
	public String search(@Validated @ModelAttribute SearchForm seForm, BindingResult result, Model model,
			@AuthenticationPrincipal LoginUserDetails userDetails, RedirectAttributes redirect, HttpServletRequest request) {
		if(result.hasErrors()) {
			String uri = request.getHeader("REFERER").substring(21);
			redirect.addFlashAttribute("error", "システムエラー");
			return "redirect:" + uri;
		}
		
		Integer loginUserId = userDetails.getUserId();
		List<BookNames> list = searchService.searchList(seForm, loginUserId);
		
		//　GenreID
		if (seForm.getGenreId() != null && seForm.getGenreId() != 0) {
		    Genre genre = genreService.findById(seForm.getGenreId());
		    model.addAttribute("genreName", genre.getName());
		} else {
		    // ジャンル未選択時
		    model.addAttribute("genreName", "");
		}
		
		model.addAttribute("user", userDetails.getLastName() + " " + userDetails.getFirstName());
		model.addAttribute("seForm", seForm);
		model.addAttribute("list", list);
		
		return "books/searchbooks";
	}
}
