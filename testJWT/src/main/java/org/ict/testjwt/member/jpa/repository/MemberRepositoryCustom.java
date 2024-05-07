package org.ict.testjwt.member.jpa.repository;

import org.ict.testjwt.common.SearchDate;
import org.ict.testjwt.member.jpa.entity.MemberEntity;
import org.springframework.data.domain.Pageable;

import java.util.*;

public interface MemberRepositoryCustom {

    /* JPA가 제공하지 않는 기능을 작동시키기 위한 메서드 추가함. */

    // 회원가입시 중복 체크용
    MemberEntity findByUserId(String userId);

    // 로그인 가능여부 변경용 (관리자)
    int UpdateLoginOK(String userId, String loginOk);

    // 관리자용 검색 관련
    long searchIDCount(String keyword);
    long searchGenderCount(String keyword);
    long searchAgeCount(int age);
    long searchDateCount(SearchDate searchDate);
    long searchLoginOkCount(String keyword);

    List<MemberEntity> findBySearchUserId(String keyword, Pageable pageable);
    List<MemberEntity> findBySearchGendar(String keyword, Pageable pageable);
    List<MemberEntity> findBySearchAge(int age, Pageable pageable);
    List<MemberEntity> findBySearchEnrollDate(SearchDate searchDate, Pageable pageable);
    List<MemberEntity> findBySearchLoginOK(String keyword, Pageable pageable);

}
