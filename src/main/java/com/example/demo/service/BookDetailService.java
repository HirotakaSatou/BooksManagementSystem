package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.BookDetail;

public interface BookDetailService {

	Optional<BookDetail> selectOne(Integer id, Integer loginUserId);
	
	Integer bookCount(Integer lendBookId);

}
