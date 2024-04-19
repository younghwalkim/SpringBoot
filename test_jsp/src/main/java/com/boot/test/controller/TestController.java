package com.boot.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.test.dto.Board;
import com.boot.test.service.TestService;


@RestController
public class TestController {
	
	@Autowired
	private TestService testService;

	// 루트 진입시 /home 리다이렉트
	@GetMapping("/")
	public ModelAndView root(ModelAndView mv) {
		mv.setViewName("redirect:/home");
		return mv;
	}

	// 상단 메뉴
	@GetMapping("/menubar")
	public String welcome() {
		String str = "Welcome! Spring Boot Project with JSP <br>";
		str += "[ <a href='/home'>Home</a> | ";
		str += "<a href='/member'>member</a> | ";
		str += "<a href='/board'>board</a> ] ";

		return str;
	}

	// home
	@GetMapping("/home")
	public ModelAndView home(ModelAndView mv) {
		mv.addObject("name", "홍길동");
		mv.setViewName("home");
		return mv;
	}

	// Board List
	@GetMapping("/board")
	public ModelAndView board(ModelAndView mv) {

		List<Board> list = testService.selectList();

		mv.addObject("list", list);
		mv.setViewName("board/boardList");
		return mv;
	}

	// Board 등록페이지 이동
	@GetMapping("/movepage")
	public ModelAndView moveBoardPage(ModelAndView mv) {
		mv.setViewName("board/boardWritePage");
		return mv;
	}
}






