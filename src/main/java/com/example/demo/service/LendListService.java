package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.LendList;

public interface LendListService {

	List<LendList> myLendList(Integer loginUserId);
}
