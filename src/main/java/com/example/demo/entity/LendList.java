package com.example.demo.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LendList {

	private String title;
	private String img;
	private LocalDate lendDate;
	private LocalDate returnDueDate;
	
	//返却期限までの日数表示
	private String dueMessage;
	private String dueMessageClass;

	public String getDueMessage() {
	    return dueMessage;
	}
	public void setDueMessage(String dueMessage) {
	    this.dueMessage = dueMessage;
	}
	public String getDueMessageClass() {
	    return dueMessageClass;
	}
	public void setDueMessageClass(String dueMessageClass) {
	    this.dueMessageClass = dueMessageClass;
	}

	
}
