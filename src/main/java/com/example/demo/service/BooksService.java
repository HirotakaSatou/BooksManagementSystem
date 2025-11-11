package com.example.demo.service;

public interface BooksService {

	Integer getBook(Integer bookNameId);
	
	void update(Integer lendBookId);
	
	void resetBooks();
	
}
