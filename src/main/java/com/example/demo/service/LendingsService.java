package com.example.demo.service;

import java.sql.Timestamp;
import java.time.LocalDate;

public interface LendingsService {
	
	Integer sameBook(Integer bookNameId, Integer loginUserId);
	
	void insert(Integer bookId, 
			Integer userId,
			LocalDate lendDate,
			LocalDate returnDueDate,
			LocalDate returnDate,
			Timestamp createdAt,
			Timestamp updatedAt);
	
	void resetLendings();
}
