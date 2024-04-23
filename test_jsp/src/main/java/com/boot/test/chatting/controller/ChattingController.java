package com.boot.test.chatting.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/chatting")
public class ChattingController {
    
	/*
    // 1. 채팅 생성 및 대기 페이지 이동
    @RequestMapping("chattingPage.do")
    public String chatting() {
        return "chatting/unicast";
    }

    // 채팅 요청 입력
    @RequestMapping(value="chatRequest.do", method= RequestMethod.POST)
    public void chatRequest(
    		@RequestParam("textMessage") String textMessage,
    		@RequestParam("sender") String sender,
    		@RequestParam("receiver") String receiver) {
    	
    	// 채팅방 개설 - 채팅방 내용, 초대자, 상대방 
    	Message message = new Message(textMessage, sender, receiver, "Y");
    	
    	// 채팅방 개설 - 채팅정보 insert
    	chattingService.insertMessage(message);
    }
    
    // 채팅 대상 확인
    @RequestMapping(value="checkMember.do", method= RequestMethod.POST)
    public void checkMember(
    	@RequestParam("memberID") String memberID, 
    	HttpServletResponse response) throws IOException {
    	
    	int result = 0;
    	
    	// 회원테이블에 초대한 사람의 이름이 있는지 확인
    	result = chattingService.selectReceiver(memberID);
    	
		String returnStr = null;
		
		if(result > 0) {
			returnStr = "exist";
		}else {
			returnStr = "failed";
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnStr);
		out.flush();
		out.close();
	}
    
    
    // 채팅 요청 여부 확인
    @RequestMapping("enterChat.do")
    public String chatResponse(
    		HttpServletRequest request, 
    		Model model) {
    	
    	//
    	Member member = (Member)request.getSession().getAttribute("loginMember");
    	
    	// Message message = chattingService.selectChatRequest(member.getMemberID());
    	Message message = chattingService.selectChatRequest(member.getName());
    	
    	if(message != null) {
    		model.addAttribute("sender", message.getSender());
    		return "chatting/unicast2";
    	}else {
    		model.addAttribute("error", "잘못된 요청입니다.");
    		return "common/error";
    	}
    }
    
    // 채팅 요청 응답 또는 연결 종료 시  요청대기 삭제
    @RequestMapping(value="removeRequest.do", method=RequestMethod.POST)
    public void removeRequest(
    		@RequestParam("sender") String sender,
    		@RequestParam("receiver") String receiver) {
    	
    	Message message = new Message();
    	message.setReceiver(receiver);
    	message.setSender(sender);
    	
    	chattingService.deleteMessage(message);
    }
    
    // 채팅 요청 응답 또는 연결 종료 시  요청대기 삭제
    @RequestMapping(value="cleanChat.do", method=RequestMethod.POST)
    public void cleanChat(HttpSession session) {
    	session.removeAttribute("chatOn");
    }
    */

}