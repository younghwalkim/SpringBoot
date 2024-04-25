package org.ict.testjpa2.board.jpa.repository;

import org.ict.testjpa2.board.jpa.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

// Mybatis 의 SqlSession 과 같은 역할 수행함, Mybatis 의 Mapper 인터페이스와 같음
// JPA의 Repository 는 JpaRepository 를 상속받아서 만듦.
// 제네릭스는 <엔티티클래스명, @id 프로퍼티의 클래스 자료형> 표기함.

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    /* 방법 1. JPQL을 이용한 Repository */


    /* 방법 2. @Query + NativeQuery 사용 (테이블명과 컬럼명을 사용) */
    /* 단, NativeQuery 사용시 컬럼명과 같은 get메소드로만 구성된  nativeVo 인터페이스가 필요함. */

    // Top 3 출력용
    @Query(value = " select * "
            + " from board order by board_readcount desc ", nativeQuery=true)
    List<BoardEntity> findTop3();

    // 제목검색 갯수
    @Query(value = " select count(*) from board where board_title like %:keyword%", nativeQuery=true)
    Long countSearchTitle(@Param("keyword") String keyword);

    // 등록자검색 갯수
    @Query(value = " select count(*) from board where board_writer like %:keyword%", nativeQuery=true)
    Long countSearchWriter(@Param("keyword") String keyword);

    // 날짜검색 갯수
    @Query(value = " select count(*) from board where board_date between :begin and :end ", nativeQuery=true)
    Long countSearchDate(@Param("begin") java.sql.Date begin, @Param("end") java.sql.Date end );


    /* 방법 3. @Query + JPQL 사용 (엔티티와 프로퍼티 사용) */
    // 제목 검색
    @Query(value="select b from BoardEntity b where b.boardTitle like %:keyword% ",
            countQuery="select count(b) from BoardEntity b where b.boardTitle like %:keyword% ")
    Page<BoardEntity> findSearchTitle(
            @Param("keyword") String keyword,
            @Param("pageable") Pageable pageable);

    // 등록자 검색
    @Query(value="select b from BoardEntity b where b.boardWriter like %:keyword% ",
            countQuery="select count(b) from BoardEntity b where b.boardWriter like %:keyword% ")
    Page<BoardEntity> findSearchWriter(
            @Param("keyword") String keyword,
            @Param("pageable") Pageable pageable);

    // 날짜 검색
    @Query(value="select b from BoardEntity b where b.boardDate between :begin and :end ",
            countQuery="select count(b) from BoardEntity b where b.boardDate between :begin and :end ")
    Page<BoardEntity> findSearchDate(
            @Param("begin") Date begin,
            @Param("end") Date end,
            @Param("pageable") Pageable pageable);

}
