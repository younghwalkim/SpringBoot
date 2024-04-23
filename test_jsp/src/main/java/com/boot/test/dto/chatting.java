package com.boot.test.dto;

import lombok.Data;

@Data
public class chatting{

    private String textMessage; // 메시지 내용
    private String sender; // 보낸 회원 아이디
    private String receiver; // 받는 회원 아이디
    private String connectYN;	// 대기 중 여부

}