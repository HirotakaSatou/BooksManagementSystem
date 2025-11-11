package com.example.demo.controller.books;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.form.LendingForm;
import com.example.demo.form.SearchForm;
import com.example.demo.service.BookDetailService;
import com.example.demo.service.BooksService;
import com.example.demo.service.LendingsService;
import com.example.demo.service.LoginUserDetails;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 貸出完了コントローラ
 */

@Controller
@RequestMapping("/")
public class LendCompleteController {

	@Autowired
	BookDetailService detailService;
	
	@Autowired
	LendingsService lendingsService;
	
	@Autowired
	BooksService booksService;
	
	// 貸出完了 - 貸出完了処理
	@PostMapping("/complete")
	public String complete(@Validated @ModelAttribute LendingForm leForm, BindingResult result, @ModelAttribute SearchForm seForm,
			Model model, RedirectAttributes redirect, @AuthenticationPrincipal LoginUserDetails userDetails, HttpServletRequest request) {
		
		if(result.hasErrors()) {
			String uri = request.getHeader("REFERER").substring(21);
			redirect.addFlashAttribute("error", "システムエラー");
			return "redirect:" + uri;
		}
		
		Long datetime = System.currentTimeMillis();
		Timestamp time = new Timestamp(datetime);
		LocalDate today = LocalDate.now();
		LocalDate due = today.plusDays(14);
		Integer lendBook = booksService.getBook(leForm.getBookNameId());
		Integer sameBook = lendingsService.sameBook(leForm.getBookNameId(), userDetails.getUserId());
		model.addAttribute("user", userDetails.getLastName() + " " + userDetails.getFirstName());
		model.addAttribute("seForm", seForm);
		
		// 同一書籍の貸し出しをしているかチェック
		if (sameBook != 0) {
			model.addAttribute("complete", false);
			model.addAttribute("title", "貸出処理エラー");
			model.addAttribute("message", "この書籍を借りています。");
			model.addAttribute("seForm", seForm);
			return "books/complete";
		}
		
		// 貸出状況を再確認し、貸出中なら登録処理を行わず完了画面に遷移させる
		if (lendBook == 0) {
			model.addAttribute("complete", false);
			model.addAttribute("title", "貸出処理エラー");
			model.addAttribute("message", "この書籍は在庫がありません。");
			model.addAttribute("seForm", seForm);
			return "books/complete";
		}
		// lendingsテーブルの登録
		lendingsService.insert(lendBook, userDetails.getUserId(), today, due, null, time, time);
		
		// booksテーブルの変更
		booksService.update(lendBook);
		
		model.addAttribute("complete", true);
		model.addAttribute("title", "貸出完了");
		model.addAttribute("message", "貸出処理が完了しました。");
		return "books/complete";
	}
	
	@PostMapping("/ajax/complete")
	@ResponseBody
	public Map<String, Object> completeAjax(@Validated @RequestBody LendingForm leForm,
			BindingResult result, @AuthenticationPrincipal LoginUserDetails userDetails) {
		
		if (result.hasErrors()) {
			return Map.of("message", "システムエラー", "complete", "false");
		}
		
		Integer lendBook = booksService.getBook(leForm.getBookNameId());
		Integer bookCount = detailService.bookCount(leForm.getBookNameId());
		Integer sameBook = lendingsService.sameBook(leForm.getBookNameId(), userDetails.getUserId());
		
		// 同一書籍の貸し出しをしているかチェック
		if (sameBook != 0) {
			return Map.of("message", "この書籍を借りています。", "complete", "false");
		}
		
		//貸出状況を再度チェックする
		if (lendBook == 0) {
			return Map.of("message", "この書籍は在庫がありません。", "complete", "false");
		}
		
		Long datetime = System.currentTimeMillis();
		Timestamp time = new Timestamp(datetime);
		LocalDate today = LocalDate.now();
		LocalDate due = today.plusDays(14);
		
		// lendingsテーブルの登録
		lendingsService.insert(leForm.getBookNameId(), userDetails.getUserId(), today, due, null, time, time);
		
		// booksテーブルの変更
		booksService.update(lendBook);
		
		Integer count = bookCount - 1;
		return Map.of("message", "貸出処理が完了しました。", "complete", "true", "count", count);
	}
}
