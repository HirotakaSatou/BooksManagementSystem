package com.example.demo.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookNames {
	
	@Id
	private int id;
	private String title;
	private String author;
	private String detail;
	private String name;
	private String img;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private int lendCount;
	private String publisher;
	private boolean sameBook;
	
}
