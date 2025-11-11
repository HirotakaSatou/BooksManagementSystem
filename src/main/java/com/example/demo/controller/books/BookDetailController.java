package com.example.demo.controller.books;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.BookDetail;
import com.example.demo.form.SearchForm;
import com.example.demo.service.BookDetailService;
import com.example.demo.service.LendingsService;
import com.example.demo.service.LoginUserDetails;

/**
 * 書籍詳細コントローラ
 */

@Controller
@RequestMapping("/book/{id}")
public class BookDetailController {

	@Autowired
	BookDetailService detailService;
	@Autowired
	LendingsService lendingsService;
	
	// 書籍詳細 - 画面表示
	@GetMapping
	public String detail(@ModelAttribute SearchForm seForm, @PathVariable Integer id, Model model, @AuthenticationPrincipal LoginUserDetails userDetails) {
		Integer loginUserId = userDetails.getUserId();
		Optional<BookDetail> optional = detailService.selectOne(id, loginUserId);
		
		// 同一書籍貸出チェック
	    Integer sameBookCount = lendingsService.sameBook(id, loginUserId);
	    boolean sameBook = (sameBookCount != null && sameBookCount > 0);
	    // 返却期限計算
		LocalDate due = LocalDate.now().plusWeeks(2);
        String dueDateStr = due.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
		
		model.addAttribute("user", userDetails.getLastName() + " " + userDetails.getFirstName());
		model.addAttribute("seForm", seForm);
		model.addAttribute("optional", optional.get());
		model.addAttribute("dueDate", dueDateStr);
		
		model.addAttribute("sameBook", sameBook);
		return "books/bookdetail";
	}
}
