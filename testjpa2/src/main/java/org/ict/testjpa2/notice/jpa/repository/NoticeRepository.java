package org.ict.testjpa2.notice.jpa.repository;

import org.ict.testjpa2.notice.jpa.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

// Mybatis 의 SqlSession 과 같은 역할 수행함, Mybatis 의 Mapper 인터페이스와 같음
// JPA의 Repository 는 JpaRepository 를 상속받아서 만듦.
// 제네릭스는 <엔티티클래스명, @id 프로퍼티의 클래스 자료형> 표기함.

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer> {
    // jpa 가 제공하는 기본메서드를 사용하려면 필요함.
    /* 방법 1. JPQL을 이용한 Repository */

    /* 방법 2. @Query + NativeQuery 사용 (테이블명과 컬럼명을 사용) */
    /* 단, NativeQuery 사용시 컬럼명과 같은 get메소드로만 구성된  nativeVo 인터페이스가 필요함. */

    // Top 3 출력용
    @Query(value = " select NOTICENO, NOTICETITLE, NOTICEDATE "
            + " from NOTICE order by NOTICEDATE desc ", nativeQuery=true)
    List<zzz_NoticeNativeVo> findTop3();

}
