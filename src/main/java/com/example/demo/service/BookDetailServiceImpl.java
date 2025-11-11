package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.BookDetail;

@Service
@Transactional
public class BookDetailServiceImpl implements BookDetailService {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	public BookDetailServiceImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Optional<BookDetail> selectOne(Integer id, Integer loginUserId) {
		String sql = "select bn.id, title, author, detail, publisher, name, img, "
				+ "(select count(lendable) from book_names bn left join books b on bn.id = b.book_name_id where lendable = 1 and bn.id = :id) lend_count "
			+ "from book_names bn "
			+ "left join books b on bn.id = b.book_name_id "
			+ "left join genre g on genre = g.id "
			+ "where bn.id = :id "
			+ "and bn.active = 1 "
			+ "and b.active = 1 "
			+ "group by bn.id, title, author, detail, publisher, name, img "
			+ "limit 1";
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		param.put("loginUserId", loginUserId);
		Map<String, Object> detail = jdbcTemplate.queryForMap(sql, param);
		
		BookDetail bookDetail = new BookDetail();
		bookDetail.setId((Integer)detail.get("id"));
		bookDetail.setTitle((String)detail.get("title"));
		bookDetail.setAuthor((String)detail.get("author"));
		bookDetail.setDetail((String)detail.get("detail"));
		bookDetail.setPublisher((String)detail.get("publisher"));
		bookDetail.setName((String)detail.get("name"));
		bookDetail.setImg((String)detail.get("img"));
		bookDetail.setLendCount(((Long)detail.get("lend_count")).intValue());
		Optional<BookDetail> result = Optional.ofNullable(bookDetail);
		
		return result;
	}
	
	public Integer bookCount(Integer lendBookId) {
		String sql = "select count(lendable) as lend_count "
				+ "from book_names bn "
				+ "left join books b on bn.id = b.book_name_id "
				+ "where bn.id = :lendBookId "
				+ "and b.lendable = 1 "
				+ "and bn.active = 1 "
				+ "and b.active = 1";
		Map<String, Object> param = new HashMap<>();
		param.put("lendBookId", lendBookId);
		
		try {
			return jdbcTemplate.queryForObject(sql, param, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
}

