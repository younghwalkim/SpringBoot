package com.boot.test.main.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.test.dto.Board;
import com.boot.test.main.service.TestService;


@RestController
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private TestService testService;

	// 루트 진입시 /home 리다이렉트
	@GetMapping("/")
	public ModelAndView root(ModelAndView mv) {
		mv.setViewName("redirect:/home");
		return mv;
	}

	// 상단 공통 메뉴 영역
	@GetMapping("/menubar")
	public String menubar() {
		String str = "Welcome! Spring Boot Project with JSP <br>";
		str += "[ <a href='/home'>Home</a> | ";
		str += "<a href='/board'>board</a> | ";
		str += "<a href='/chat'>chat request</a> | ";
		str += "<a href='/enterChat'>chat response</a> | ";
		str += "<a href='/contract'>member</a> ] ";

		return str;
	}

	// 하단 공통 영역
	@GetMapping("/footer")
	public String footer() {
		String str = "<center>Welcome! Spring Boot Project with JSP</center>";
		
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
	@GetMapping("/btop3")
	@ResponseBody
	public String btop3() throws UnsupportedEncodingException {

		List<Board> list = testService.selectTop3();

		logger.info("### btop3 : " + list);

		JSONArray jarr = new JSONArray();

		for(Board board : list) {
			JSONObject job = new JSONObject();

			job.put("bnum", board.getBoardNum());

			//한글 데이터는 반드시 인코딩 처리함
			job.put("btitle", URLEncoder.encode(board.getBoardTitle(), "utf-8"));
			job.put("rcount", board.getBoardReadCount());

			jarr.add(job);
		}

		JSONObject sendJson = new JSONObject();
		sendJson.put("list", jarr);

		return sendJson.toJSONString();
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

	// c-1. chat 입장
	@GetMapping("/chat")
	public ModelAndView chat(ModelAndView mv) {

		mv.setViewName("chatting/unicast");
		return mv;
	}
	
	// c-2. 채팅 생성 및 대기 페이지 이동
	@RequestMapping("chattingPage")
	public String chatting() {
		return "chatting/unicast";
	}
	
	// chat 응답
	@GetMapping("/enterChat")
	public ModelAndView enterChat(ModelAndView mv) {

		mv.setViewName("chatting/unicast2");
		return mv;
	}

	// 이용약관
	@GetMapping("/contract")
	public ModelAndView contract(ModelAndView mv) {
		mv.setViewName("member/contract");
		return mv;
	}

	// 등록양식
	@GetMapping("/registerPage")
	public ModelAndView registerPage(ModelAndView mv) {
		mv.setViewName("member/register");
		return mv;
	}
}






