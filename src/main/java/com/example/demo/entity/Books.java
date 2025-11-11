package com.example.demo.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {

	@Id
	private int id;
	private int bookNameId;
	private int active;
	private int lendable;
	private Timestamp createdAt;
	private Timestamp updatedAt;
}
