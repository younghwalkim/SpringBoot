package com.boot.test.member.service;

import com.boot.test.dto.Member;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;


public interface MemberService {

	// 등록
	int register(Member member);
	// 로그인
	Member login(Member member);
	// 이메일체크
	int selectCheckEmail(String userId);
	boolean accountCheck(Member member);
	Member selectMember(String email);
	// 비밀번호 수정
	void updatePassword(Member member);
	// 비밀번호 확인
	Member selectCheckPwd(String email);
	// 회원정보 수정
	int updateMember(Member loginMember);
	// 회원탈퇴
	int deleteMember(String email);

}
