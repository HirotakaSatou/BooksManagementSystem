package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetail {

	@Id
	private int id;
	private String title;
	private String author;
	private String detail;
	private String img;
	private String name;
	private int lendCount;
	private String publisher;
}
