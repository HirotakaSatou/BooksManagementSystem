package com.example.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.BookNames;
import com.example.demo.form.SearchForm;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final LendingsService lendingsService;

    public SearchServiceImpl(NamedParameterJdbcTemplate jdbcTemplate, LendingsService lendingsService) {
        this.jdbcTemplate = jdbcTemplate;
        this.lendingsService = lendingsService;
    }

    @Override
    public List<BookNames> searchList(SearchForm form, Integer loginUserId) {

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT bn.id, title, author, detail, publisher, name, img, " +
                "count(CASE WHEN lendable = 1 THEN 1 ELSE NULL END) AS lend_count " +
                "FROM book_names bn " +
                "LEFT JOIN genre g ON bn.genre = g.id " +
                "LEFT JOIN books b ON bn.id = b.book_name_id " +
                "WHERE bn.active = 1 ");

        Map<String, Object> param = new HashMap<>();
        param.put("loginUserId", loginUserId);

        // キーワード検索
        if (form.getSearchWord() != null && !form.getSearchWord().trim().isEmpty()) {
            sqlBuilder.append("AND (bn.title ILIKE :searchWord OR bn.author ILIKE :searchWord OR bn.publisher ILIKE :searchWord) ");
            param.put("searchWord", "%" + form.getSearchWord().trim() + "%");
        }

        // ジャンル検索
        if (form.getGenreId() != null && form.getGenreId() != 0) {
            sqlBuilder.append("AND bn.genre = :genreId ");
            param.put("genreId", form.getGenreId());
        }

        sqlBuilder.append("GROUP BY bn.id, title, author, detail, publisher, name, img");

        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sqlBuilder.toString(), param);

        List<BookNames> list = new ArrayList<>();
        for (Map<String, Object> result : resultList) {
            BookNames bookNames = new BookNames();
            bookNames.setId((Integer) result.get("id"));
            bookNames.setTitle((String) result.get("title"));
            bookNames.setAuthor((String) result.get("author"));
            bookNames.setDetail((String) result.get("detail"));
            bookNames.setPublisher((String) result.get("publisher"));
            bookNames.setName((String) result.get("name"));
            bookNames.setImg((String) result.get("img"));
            bookNames.setCreatedAt((Timestamp) result.get("createdAt"));
            bookNames.setUpdatedAt((Timestamp) result.get("updatedAt"));
            bookNames.setLendCount(((Long) result.get("lend_count")).intValue());

            // 同一書籍チェック
            Integer count = lendingsService.sameBook(bookNames.getId(), loginUserId);
            bookNames.setSameBook(count > 0);

            list.add(bookNames);
        }

        return list;
    }
}
