package com.example.demo.repository;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;

@Repository
public class LoginUserRepository {

    private static final String SQL_FIND_BY_EMAIL = """
            SELECT
              id, last_name, first_name, email, password, role, created_at, updated_at
            FROM users
            WHERE email = :email
            """;
	
    private static final ResultSetExtractor<Users> LOGIN_USER_EXTRACTOR = (rs) -> {
    	Integer id = null;
        String email = null;
        String lastName = null;
        String firstName = null;
        String password = null;
        Integer role =  null;
        Timestamp createdAt = null;
        Timestamp updatedAt = null;
        while (rs.next()) {
            if (email == null) {
            	id = rs.getInt("id");
                email = rs.getString("email");
                lastName = rs.getString("last_name");
                firstName = rs.getString("first_name");
                password = rs.getString("password");
                role = rs.getInt("role");
                createdAt = rs.getTimestamp("created_at");
                updatedAt = rs.getTimestamp("updated_at");
            }
        }
        if (email == null) {
            return null;
        }
        return new Users(id, lastName, firstName, email, password, role, createdAt, updatedAt);
    };

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public LoginUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Optional<Users> findByEmail(String email) {
        MapSqlParameterSource params = new MapSqlParameterSource("email", email);
        Users Users = namedParameterJdbcTemplate.query(SQL_FIND_BY_EMAIL, params, LOGIN_USER_EXTRACTOR);
        return Optional.ofNullable(Users);
    }
}