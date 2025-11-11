package com.example.demo.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LendingsServiceImpl implements LendingsService {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	public LendingsServiceImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Integer sameBook(Integer bookNameId, Integer loginUserId) {
		String sql = "select count(*) from lendings l left join books b on b.id = l.book_id "
				+ "where l.return_date is null and b.book_name_id = :bookNameId and l.user_id = :loginUserId";
		Map<String, Object> param = new HashMap<>();
		param.put("bookNameId", bookNameId);
		param.put("loginUserId", loginUserId);
		
		try {
			return jdbcTemplate.queryForObject(sql, param, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
	public void insert(Integer bookId, Integer userId, LocalDate lendDate, LocalDate returnDueDate, LocalDate returnDate, Timestamp createdAt, Timestamp updatedAt) {
		String sql = "insert into lendings(book_id, user_id, lend_date, return_due_date, return_date, created_at, updated_at) "
				+ "values(:bookId, :userId, :lendDate, :returnDueDate, :returnDate, :createdAt, :updatedAt)";
		Map<String, Object> param = new HashMap<>();
		param.put("bookId", bookId);
		param.put("userId", userId);
		param.put("lendDate", lendDate);
		param.put("returnDueDate", returnDueDate);
		param.put("returnDate", returnDate);
		param.put("createdAt", createdAt);
		param.put("updatedAt", updatedAt);
		
		jdbcTemplate.update(sql, param);
	}

	public void resetLendings() {
		String sql = "delete from lendings where return_date is null";
		Map<String, Object> param = new HashMap<>();
		
		jdbcTemplate.update(sql, param);
	}
}
