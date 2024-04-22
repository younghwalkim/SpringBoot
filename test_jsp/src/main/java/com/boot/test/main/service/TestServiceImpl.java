package com.boot.test.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.test.dto.Board;
import com.boot.test.main.mapper.TestMapper;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestMapper testMapper;
	
	@Override
	public List<Board> selectList() {
		return testMapper.selectBoardList();
	}

	@Override
	public List<Board> selectTop3() {
		return testMapper.selectTop3();
	}

}
