package org.ict.testjwt.member.model.service;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ict.testjwt.common.SearchDate;
import org.ict.testjwt.member.jpa.entity.MemberEntity;
import org.ict.testjwt.member.jpa.repository.MemberRepository;
import org.ict.testjwt.member.model.dto.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberDto insertMember(MemberDto member) {
        //save() -> 성공시 Entity, 실패시 null 리턴함, JPA 가 제공하는 메소드임
        return memberRepository.save(member.toEntity()).toDto();
    }

    public MemberDto selectMember(String userid) {
        //repository 에 메소드 추가함
        return memberRepository.findByUserId(userid).toDto();  //userId 를 이용한 회원 정보 select 요청
    }

    @Transactional
    public MemberDto updateMember2(MemberDto member) {
        //패스워드 수정이 있는 경우
        //save() -> 성공시 Entity, 실패시 null 리턴함
        return memberRepository.save(member.toEntity()).toDto();
    }

    @Transactional
    public int deleteMember(String userid) {
        try {   //리턴 타입을 int 로 맞추기 위해서 처리함
            //deleteById() -> 리턴 타입이 void 임
            //전달인자인 userid 가 null 인 경우 IllegalArgumentException 발생함
            memberRepository.deleteById(userid);
            return 1;
        } catch (Exception e) {
            log.info(e.getMessage());
            return 0;
        }
    }

    //페이징 처리된 목록 조회
    public ArrayList<MemberDto> selectList(Pageable pageable) {
        Page<MemberEntity> entityPage = memberRepository.findAll(pageable);
        ArrayList<MemberDto> list = new ArrayList<>();
        for(MemberEntity entity : entityPage){
            list.add(entity.toDto());
        }
        return list;
    }

    public long selectListCount() {
        return memberRepository.count();
    }

    @Transactional
    public int updateLoginOK(MemberDto member) {
        //수정에 필요한 항목만 추출
        String userId = member.getUserId();
        String loginOk = member.getLoginOk();
        return memberRepository.UpdateLoginOK(userId, loginOk);
    }

    //반복 사용 코드는 메소드로 만들어서 이용함 -----------------------------------
    private ArrayList<MemberDto> toList(List<MemberEntity> entityList){
        ArrayList<MemberDto> list = new ArrayList<>();
        for(MemberEntity entity : entityList){
            list.add(entity.toDto());
        }
        return list;
    }

    //관리자용 검색 관련
    public ArrayList<MemberDto> selectSearchUserid(String keyword, Pageable pageable) {
        return toList(memberRepository.findBySearchUserId(keyword, pageable));
    }

    public ArrayList<MemberDto> selectSearchGender(String keyword, Pageable pageable) {
        return toList(memberRepository.findBySearchGendar(keyword, pageable));
    }

    public ArrayList<MemberDto> selectSearchAge(int age, Pageable pageable) {
        return toList(memberRepository.findBySearchAge(age, pageable));
    }

    public ArrayList<MemberDto> selectSearchEnrollDate(SearchDate searchDate, Pageable pageable) {
        return toList(memberRepository.findBySearchEnrollDate(searchDate, pageable));
    }

    public ArrayList<MemberDto> selectSearchLoginOK(String keyword, Pageable pageable) {
        return toList(memberRepository.findBySearchLoginOK(keyword, pageable));
    }

    public boolean selectCheckId(String userId) {
        //log.info("MemberService.selectCheckId : " + userId);
        //가입회원 아이디 중복 검사용
        return memberRepository.existsById(userId);
        //존재하면 true, 존재하지 않으면 false 리턴함
    }

    //검색 목록 카운트 관련
    public int selectSearchIDCount(String keyword) {
        return (int)memberRepository.searchIDCount(keyword);
    }

    public int selectSearchGenderCount(String keyword) {
        return (int)memberRepository.searchGenderCount(keyword);
    }

    public int selectSearchAgeCount(int age) {
        return (int)memberRepository.searchAgeCount(age);
    }

    public int selectSearchDateCount(SearchDate searchDate) {
        return (int)memberRepository.searchDateCount(searchDate);
    }

    public int selectSearchLoginOKCount(String keyword) {
        return (int)memberRepository.searchLoginOkCount(keyword);
    }
}
