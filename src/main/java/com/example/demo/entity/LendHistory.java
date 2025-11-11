package com.example.demo.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LendHistory {

	private int id;
	private int bookNameId;
	private String title;
	private String author;
	private String detail;
	private String publisher;
	private String name;
	private String img;
	private LocalDate lendDate;
	private LocalDate returnDate;
	private int lendCount;
	
	private boolean sameBook;
}
