package com.example.demo.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lendings {

	@Id
	private int id;
	private int bookId;
	private int userId;
	private LocalDate lendDate;
	private LocalDate returnDueDate;
	private LocalDate returnDate;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
}
