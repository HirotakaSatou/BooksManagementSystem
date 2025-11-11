package com.example.demo.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LendList;

@Service
public class LendListServiceImpl implements LendListService {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	public LendListServiceImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
    }
	
	public List<LendList> myLendList(Integer loginUserId) {
		String sql = "select title, img, lend_date, return_due_date "
				+ "from lendings l "
				+ "inner join books b on b.id = l.book_id "
				+ "inner join book_names bn on bn.id = b.book_name_id "
				+ "inner join genre g on g.id = bn.genre "
				+ "where bn.active = 1 "
				+ "and b.active = 1 "
				+ "and l.return_date is null "
				+ "and user_id = :loginUserId "
				+ "order by return_due_date";
		Map<String, Object> param = new HashMap<>();
		param.put("loginUserId", loginUserId);
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
		
		List<LendList> list = new ArrayList<LendList>();
		for(Map<String, Object> result : resultList) {
			LendList lendList = new LendList();
			lendList.setTitle((String)result.get("title"));
			lendList.setImg((String)result.get("img"));
			lendList.setLendDate(((Date)result.get("lend_date")).toLocalDate());
			lendList.setReturnDueDate(((Date)result.get("return_due_date")).toLocalDate());
			list.add(lendList);
		}
		return list;
	}

}
