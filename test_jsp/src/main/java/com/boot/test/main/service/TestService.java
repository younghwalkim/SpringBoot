package com.boot.test.main.service;

import java.util.List;

import com.boot.test.dto.Board;

public interface TestService {
	List<Board> selectList();

    List<Board> selectTop3();
}
