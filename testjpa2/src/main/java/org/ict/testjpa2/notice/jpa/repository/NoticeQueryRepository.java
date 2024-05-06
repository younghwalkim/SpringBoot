package org.ict.testjpa2.notice.jpa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.ict.testjpa2.common.SearchDate;
import org.ict.testjpa2.notice.jpa.entity.NoticeEntity;
import org.ict.testjpa2.notice.jpa.entity.QNoticeEntity;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class NoticeQueryRepository {
    private final SearchDate searchDate;

    // 방법 3. 상속, 재구현 (implement) 없는 querydsl 만으로 구성하는 repository class 작성 방법

    private JPAQueryFactory queryFactory;
    private final EntityManager entityManager;  // JPQL 사용을 위한 의존성 주입.
    private QNoticeEntity qNoticeEntity = QNoticeEntity.noticeEntity;
    
    // 조회수 1증가 처리 => jpa 제공 메서드로 save() 사용해도 됨.
    public void updateReadCount(int noticeNo){
        // querydsl 사용
        queryFactory
                .update(qNoticeEntity)                                          // update notice
                .set(qNoticeEntity.readCount, qNoticeEntity.readCount.add(1))   // set readcount = readcount + 1
                .where(qNoticeEntity.noticeNo.eq(noticeNo))                     // where noticeno = 전달된 글 번호
                .execute();
        entityManager.flush();
        entityManager.clear();
    }

    // 가장 최근 공지글 1개 조회
    public NoticeEntity findLast(){
        // querydsl 사용
        return queryFactory
                .selectFrom(qNoticeEntity)              // select * from notice
                .orderBy(qNoticeEntity.noticeNo.desc()) // order by noticeno desc
                .fetch().get(0);                        //내림차순 정렬의 첫번째 =  최근 등록글 1개
    }

    // 최근 등록 Top3 조회 : querydsl 에서 jpql 사용하는 경우.
    public List<NoticeEntity> findNewTop3() {
        // 주의 : querydsl에서는 select 절과 where 절에서의 서브쿼리는 지원하지만, from 절에서의 서브쿼리는 지원하지 않는다.
        //      => 쿼리는 나눠서 실행 또는 join 등으로 해결할 필요가 있음.
        // JPQL 도 where 절과 group by 절에서만 서브쿼리 사용 가능함. from 절에서 서브쿼리 사용 못함.

        return entityManager
                .createNativeQuery("select noticeNo, noticeTitle, noticeDate from Notice order by noticeDate desc")
                .getResultList();
    }

    /* 검색 paging 처리를 위한 검색 count method */
    // 제목
    public long countByTitle(String keyword){
        // querydsl 사용
        return queryFactory
                .selectFrom(qNoticeEntity)
                .where(qNoticeEntity.noticeTitle.like("%"+ keyword + "%"))  //where noticeTitle like %검색단어%
                .fetchCount();
    }

    // 내용
    public long countByContent(String keyword){
        // querydsl 사용
        return queryFactory
                .selectFrom(qNoticeEntity)
                .where(qNoticeEntity.noticeContent.like("%"+ keyword + "%"))  //where noticeContent like %검색단어%
                .fetchCount();
    }

    // 등록일
    public long countBySearchDate(SearchDate searchDate){
        // querydsl 사용
        return queryFactory
                .selectFrom(qNoticeEntity)
                .where(qNoticeEntity.noticeDate.between(searchDate.getBegin(), searchDate.getEnd()))    // where noticedate between begin and end
                .fetchCount();
    }

    /* 검색 처리를 위한 검색 method */
    // 제목
    public List<NoticeEntity> findBySearchTitle(String keyword, Pageable pageable){
        // querydsl 사용
        return queryFactory
                .selectFrom(qNoticeEntity)     // select * from notice
                .where(qNoticeEntity.noticeTitle.like("%"+ keyword + "%"))  //where noticeTitle like %검색단어%
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    // 내용
    public List<NoticeEntity> findBySearchContent(String keyword, Pageable pageable){
        // querydsl 사용
        return queryFactory
                .selectFrom(qNoticeEntity)
                .where(qNoticeEntity.noticeContent.like("%"+ keyword + "%"))  //where noticeContent like %검색단어%
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    // 등록일
    public List<NoticeEntity> findBySearchDate(SearchDate searchDate, Pageable pageable){
        // querydsl 사용
        return queryFactory
                .selectFrom(qNoticeEntity)
                .where(qNoticeEntity.noticeDate.between(searchDate.getBegin(), searchDate.getEnd()))    // where noticedate between begin and end
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }


}
