package org.ict.testjpa2.member.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ict.testjpa2.member.jpa.entity.MemberEntity;
import org.ict.testjpa2.member.jpa.repository.MemberRepository;
import org.ict.testjpa2.member.jpa.repository.MemberRepositoryCustom;
import org.ict.testjpa2.member.model.dto.MemberDto;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    //JPA 가 제공하는 기본 메서드 사용을 위해서는
    private final MemberRepository memberRepository;

    // list
    public ArrayList<MemberDto> selectList(Pageable pageable){
        //jpa 제공
        Page<MemberEntity> entities = memberRepository.findAll(pageable);
        ArrayList<MemberDto> list = new ArrayList<>();

        for(MemberEntity entity : entities){
            list.add(entity.toDto());
        }

        return list;
    }

    // insert
    @Transactional
    public void insertMember(MemberDto noticeDto) {
        //save(entity) : Entity
        //jpa 제공, insert 문과 update문 처리용 메서드
        memberRepository.save(noticeDto.toEntity());
    }

    // 조회
    public MemberDto selectMember(String userid){
        //jpa 제공 : findById(Id) : Optional<T>
        //엔티티에 등록된 id 에 해당하는 property 를 사용해서 조회함
        Optional<MemberEntity> entity = memberRepository.findById(userid);
        return entity.get().toDto();
    }

    // update
    @Transactional
    public void updateMember(MemberDto memberDto) {
        memberRepository.save(memberDto.toEntity());
    }

    // delete
    @Transactional
    public int deleteMember(String userid) {     //int 리턴 형태를 만든다면 아래와 같이 작성함
        try {
            //jpa 제공 메서드
            memberRepository.deleteById(userid);
            return 1;
        }catch (Exception e){
            log.error(e.getMessage());
            return 0;
        }
    }

    // 아이디 검색 카운트 추가 메소드 사용
    public long getSearchUserIdCount(String keyword){
        return memberRepository.searchIDCount(keyword);
    }

    // 아이디 검색 메소드 사용
    public ArrayList<MemberDto> selectSearchUserId(
            String keyword,
            Pageable pageable){

        log.info("### selectSearchUserId : " + keyword);

        List<MemberEntity> entities = memberRepository.findBySearchUserid(keyword, pageable);
        ArrayList<MemberDto> list = new ArrayList<>();

        for(MemberEntity entity : entities){
            list.add(entity.toDto());
        }

        return list;
    }

}
