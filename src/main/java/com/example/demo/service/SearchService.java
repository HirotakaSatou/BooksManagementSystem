package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.BookNames;
import com.example.demo.form.SearchForm;

public interface SearchService {

	// 検索結果を表示
	List<BookNames> searchList(SearchForm form, Integer loginUserId);
}
