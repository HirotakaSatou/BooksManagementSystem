package com.example.demo.service;

import java.sql.Timestamp;

public interface UsersService {

	// ユーザー登録時のユニークチェック
	String duplicate(String email);
	
	// ユーザーの新規登録
	void insert(String lastName, String firstName, String email, String password, Integer role, Timestamp createdAt, Timestamp updatedAt);
	
}
