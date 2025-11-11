package com.example.demo.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
	
	@Id
	private int id;
	private String lastName;
	private String firstName;	
	private String email;
	private String password;
	private int role;
	private Timestamp createdAt;
	private Timestamp updatedAt;
}
