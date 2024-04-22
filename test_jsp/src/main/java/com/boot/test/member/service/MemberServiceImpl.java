package com.boot.test.member.service;

import com.boot.test.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	@Override
	public int register(Member member) {
		return 0;
	}

	@Override
	public Member login(Member member) {
		return null;
	}

	@Override
	public int selectCheckEmail(String userId) {
		return 0;
	}

	@Override
	public boolean accountCheck(Member member) {
		return false;
	}

	@Override
	public Member selectMember(String email) {
		return null;
	}

	@Override
	public void updatePassword(Member member) {

	}

	@Override
	public Member selectCheckPwd(String email) {
		return null;
	}

	@Override
	public int updateMember(Member loginMember) {
		return 0;
	}

	@Override
	public int deleteMember(String email) {
		return 0;
	}
}
