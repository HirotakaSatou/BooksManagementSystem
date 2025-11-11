package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BooksServiceImpl implements BooksService {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	public BooksServiceImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	// 貸し出す書籍のIDを取得する
	public Integer getBook(Integer bookNameId) {
		String sql = "select id from books where book_name_id = :bookNameId and active = 1 and lendable = 1 limit 1";
		Map<String, Object> param = new HashMap<>();
		param.put("bookNameId", bookNameId);
		
		try {
			return jdbcTemplate.queryForObject(sql, param, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
	// 貸し出した書籍の貸出可能フラグを0にする
	public void update(Integer lendBookId) {
		String sql = "update books set lendable = 0 where id = :lendBookId";
		Map<String, Object> param = new HashMap<>();
		param.put("lendBookId", lendBookId);
		jdbcTemplate.update(sql, param);
	}
	
	// デバッグ - すべての貸出中の書籍を貸出可能にする
	public void resetBooks() {
		String sql = "update books set lendable = 1 where active = 1 and lendable = 0";
		Map<String, Object> param = new HashMap<>();
		
		jdbcTemplate.update(sql, param);
	}
	
}
