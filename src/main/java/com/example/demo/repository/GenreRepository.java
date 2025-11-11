package com.example.demo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Genre;

@Repository
public class GenreRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * ジャンル一覧を取得
     */
    public List<Genre> findAll() {
        String sql = "SELECT id, name FROM genre ORDER BY id";

        return jdbcTemplate.query(sql, (rs, i) ->
                new Genre(
                    rs.getInt("id"),
                    rs.getString("name")
                )
        );
    }

    /**
     * ジャンルIDで1件取得
     */
    public Genre findById(Integer id) {
        String sql = "SELECT id, name FROM genre WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return jdbcTemplate.queryForObject(sql, params,
                (rs, i) -> new Genre(
                        rs.getInt("id"),
                        rs.getString("name")
                )
        );
    }
}
