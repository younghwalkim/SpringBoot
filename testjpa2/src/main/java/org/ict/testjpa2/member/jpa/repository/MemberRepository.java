package org.ict.testjpa2.member.jpa.repository;

import org.ict.testjpa2.member.jpa.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository
        extends JpaRepository<MemberEntity, String>, MemberRepositoryCustom {
    //상속을 이용한 QueryDSL Repository 사용
}
