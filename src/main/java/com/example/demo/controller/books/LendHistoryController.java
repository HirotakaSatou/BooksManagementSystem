package com.example.demo.controller.books;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.LendHistory;
import com.example.demo.form.SearchForm;
import com.example.demo.service.LendHistoryService;
import com.example.demo.service.LendingsService;
import com.example.demo.service.LoginUserDetails;

/**
 * 貸出履歴コントローラ
 */

@Controller
@RequestMapping("/lendhistory")
public class LendHistoryController {

	@Autowired
	LendHistoryService lendHistoryService;
	@Autowired
	private LendingsService lendingsService;
	
	// 貸出履歴 - 画面表示
	@GetMapping
	public String history(@ModelAttribute SearchForm seForm, Model model, @AuthenticationPrincipal LoginUserDetails userDetails) {
		Integer loginUserId = userDetails.getUserId();
		List<LendHistory> lendHistory = lendHistoryService.myLendHistory(loginUserId);
		LocalDate today = LocalDate.now();
		LocalDate due = today.plusDays(14);
		
		for (LendHistory history : lendHistory) {
			Integer count = lendingsService.sameBook(history.getBookNameId(), loginUserId);
			boolean sameBook = (count != null && count > 0);
			history.setSameBook(sameBook);
		}
		
		if(lendHistory.isEmpty()) {
			model.addAttribute("none", "借りたことのある書籍はありません。");
		}
		model.addAttribute("lendHistory", lendHistory);
		model.addAttribute("user", userDetails.getLastName() + " " + userDetails.getFirstName());
		model.addAttribute("seForm", seForm);
		model.addAttribute("today", today);
		model.addAttribute("due", due);
		return "books/lendhistory";
	}
}
