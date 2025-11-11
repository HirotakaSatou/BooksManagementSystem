package com.example.demo.controller.books;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.LendList;
import com.example.demo.form.SearchForm;
import com.example.demo.service.BooksService;
import com.example.demo.service.LendListService;
import com.example.demo.service.LendingsService;
import com.example.demo.service.LoginUserDetails;

/**
 * トップページコントローラ
 */

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	LendingsService lendingsService;
	
	@Autowired
	BooksService booksService;
	
	@Autowired
	LendListService lendListService;
	
	// トップページ - 画面表示
	@GetMapping
	public String index(@ModelAttribute SearchForm seForm, @AuthenticationPrincipal LoginUserDetails userDetails, Model model) {
		Integer loginUserId = userDetails.getUserId();
		List<LendList> lendList = lendListService.myLendList(loginUserId);
		
		LocalDate today = LocalDate.now();
		for (LendList lend : lendList) {
			LocalDate dueDate = lend.getReturnDueDate();
			long days = ChronoUnit.DAYS.between(today, dueDate);
			
			if (days > 7) {
				lend.setDueMessage("あと" + days + "日");
				lend.setDueMessageClass("text-dark");
			} else if (days >= 1) {
				lend.setDueMessage("あと" + days + "日");
				lend.setDueMessageClass("text-danger");
			} else if (days == 0) {
				lend.setDueMessage("本日が返却期限です");
				lend.setDueMessageClass("text-danger");
			} else {
				lend.setDueMessage("返却期限を過ぎています");
				lend.setDueMessageClass("text-danger");
			}
		}
		
		if(lendList.isEmpty()) {
			model.addAttribute("none", "現在借りている書籍はありません。");
		}
		model.addAttribute("user", userDetails.getLastName() + " " + userDetails.getFirstName());
		model.addAttribute("seForm", seForm);
		model.addAttribute("lendList", lendList);
		return "books/index";
	}
	
	// 貸出中書籍 - リセット(デバッグ用)
	@PostMapping("/reset")
	public String returnBook(SearchForm seForm, Model model, @AuthenticationPrincipal LoginUserDetails userDetails) {
		lendingsService.resetLendings();
		
		booksService.resetBooks();
		
		model.addAttribute("user", userDetails.getLastName() + " " + userDetails.getFirstName());
		model.addAttribute("seForm", seForm);
		return "redirect:/";
	}
	
}
