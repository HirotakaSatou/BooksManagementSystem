package com.example.demo.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	public UsersServiceImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	// ユーザー登録時のユーザー重複チェック
	public String duplicate(String email) {
		String sql = "select email from users where email = :email";
		Map<String, Object> param = new HashMap<>();
		param.put("email", email);
		
		try {
			return jdbcTemplate.queryForObject(sql, param, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	// ユーザーの新規登録
	public void insert(String lastName, String firstName, String email, String password, Integer role, Timestamp createdAt, Timestamp updatedAt) {
		String sql = "insert into users(last_name, first_name, email, password, role, created_at, updated_at) "
				+ "values(:lastName, :firstName, :email, :password, :role, :createdAt, :updatedAt)";
		Map<String, Object> param = new HashMap<>();
		param.put("lastName", lastName);
		param.put("firstName", firstName);
		param.put("email", email);
		param.put("password", password);
		param.put("role", role);
		param.put("createdAt", createdAt);
		param.put("updatedAt", updatedAt);
		
		jdbcTemplate.update(sql, param);
	}
	
}
