package com.example.demo.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LendHistory;

@Service
public class LendHistoryServiceImpl implements LendHistoryService {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	public LendHistoryServiceImpl(NamedParameterJdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<LendHistory> myLendHistory(Integer loginUserId) {
		String sql = "select l.id, bn.id as book_name_id, title, author, detail, publisher, name, img, lend_date, return_date, "
				+ "(select count(*) from books b2 where lendable=1 and b.book_name_id = b2.book_name_id) lend_count "
				+ "from books b "
				+ "left join lendings l on b.id = l.book_id "
				+ "left join book_names bn on bn.id = b.book_name_id "
				+ "left join genre g on g.id = bn.genre "
				+ "where bn.active = 1 "
				+ "and b.active = 1 "
				+ "and l.return_date is not null "
				+ "and user_id = :loginUserId "
				+ "group by bn.id, book_name_id, book_id, l.id, title, author, detail, publisher, name, img, user_id, lend_date, return_date "
				+ "order by lend_date, id";
		Map<String, Object> param = new HashMap<>();
		param.put("loginUserId", loginUserId);
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
		
		List<LendHistory> list = new ArrayList<LendHistory>();
		for(Map<String, Object> result : resultList) {
			LendHistory lendHistory = new LendHistory();
			lendHistory.setId((Integer)result.get("id"));
			lendHistory.setBookNameId((Integer)result.get("book_name_id"));
			lendHistory.setTitle((String)result.get("title"));
			lendHistory.setAuthor((String)result.get("author"));
			lendHistory.setDetail((String)result.get("detail"));
			lendHistory.setPublisher((String)result.get("publisher"));
			lendHistory.setName((String)result.get("name"));
			lendHistory.setImg((String)result.get("img"));
			lendHistory.setLendDate(((Date)result.get("lend_date")).toLocalDate());
			lendHistory.setReturnDate(((Date)result.get("return_date")).toLocalDate());
			lendHistory.setLendCount(((Long)result.get("lend_count")).intValue());
			list.add(lendHistory);
		}
		return list;
	}

}
