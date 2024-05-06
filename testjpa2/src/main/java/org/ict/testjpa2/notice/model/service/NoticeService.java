package org.ict.testjpa2.notice.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ict.testjpa2.common.SearchDate;
import org.ict.testjpa2.notice.jpa.entity.NoticeEntity;
import org.ict.testjpa2.notice.jpa.repository.NoticeQueryRepository;
import org.ict.testjpa2.notice.jpa.repository.NoticeRepository;
import org.ict.testjpa2.notice.model.dto.NoticeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    // 방법 1을 위한 jpa 제공하는 기본 메소드 사용을 위해 의존성 추가
    private final NoticeRepository noticeRepository;

    // 방법 2을 위한  jpa 제공 메소드로 해결하지 못하는 SQL문 처리를 위한 별도의 리포지터리 의존성 주입함 (상송, 재구현 없는 형식)
    private final NoticeQueryRepository noticeQueryRepository;


    // Top3 목록
    public ArrayList<NoticeDto> selectTop3(){
        // 방법 1) jpa 제공 기본 메소드 사용 기능으로 구현
        // 등록날짜 기준으로 내림차순 정렬한 리스트를 받아서, 0~2 인덱스만 추출
        List<NoticeEntity> entityList = noticeRepository.findAll(Sort.by(Sort.Direction.DESC,"noticeDate"));

        // 방법 2)
        //List<NoticeEntity> entityList = noticeQueryRepository.findNewTop3();

        log.info("### entityList : "+ entityList.toString());

        ArrayList<NoticeDto> list = new ArrayList<>();

        // 내림차순정렬된 상위 3개만 추출함
        for(int i = 0; i < 3; i++){
            list.add(entityList.get(i).toDto());
        }

        return list;
    }

    // 페이징 처리를 위한 총 목록 갯수 조회용
    public long selectListCount(){
        // jpa 제공 기능으로 구현
        return noticeRepository.count();
    }

    // 목록
    public ArrayList<NoticeDto> selectList(Pageable pageable){
        // jpa 제공 기능으로 구현
        Page<NoticeEntity> entities = noticeRepository.findAll(pageable);
        ArrayList<NoticeDto> list = new ArrayList<>();

        for(NoticeEntity entity : entities) {
            list.add(entity.toDto());
        }

        return list;
    }

    // 상세조회
    public NoticeDto selectNotice(int noticeNo) {
        /* Repository -> Entity : jpa 활용하여 데이터 조회 부분 */
        Optional<NoticeEntity> entity = noticeRepository.findById(noticeNo);
        return entity.get().toDto();
    }

    // 등록
    @Transactional
    public void insertNotice(NoticeDto noticeDto) {
        /* insert 처리 (데이터가 없으면 insert) */
        noticeRepository.save(noticeDto.toEntity());
    }

    // 수정
    @Transactional
    public void updateNotice(NoticeDto noticeDto) {
        /* update 처리 (데이터가 있으면 update) */
        noticeRepository.save(noticeDto.toEntity());
    }

    // 삭제
    @Transactional
    public int deleteNotice(int noticeNo){
        /* 삭제처리 */
        try {
            // jpa 제공 메소드
            noticeRepository.deleteById(noticeNo);
            return 1;
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    // 검색 카운트 추가 메소드 사용
    public long getSearchTitleCount(String keyword){
        return noticeQueryRepository.countByTitle(keyword);
    }

    public long getSearchContentCount(String keyword){
        return noticeQueryRepository.countByContent(keyword);
    }

    public long getSearchDateCount(SearchDate searchDate){
        return noticeQueryRepository.countBySearchDate(searchDate);
    }

    // 검색 관련 추가 메소드 사용
    public ArrayList<NoticeDto> selectSearchTitle(String keyword, Pageable pageable){
        List<NoticeEntity> entities = noticeQueryRepository.findBySearchTitle(keyword, pageable);
        ArrayList<NoticeDto> list = new ArrayList<>();
        for(NoticeEntity entity : entities) {
            list.add(entity.toDto());
        }
        return list;
    }

    public ArrayList<NoticeDto> selectSearchContent(String keyword, Pageable pageable){
        List<NoticeEntity> entities = noticeQueryRepository.findBySearchContent(keyword, pageable);
        ArrayList<NoticeDto> list = new ArrayList<>();
        for(NoticeEntity entity : entities) {
            list.add(entity.toDto());
        }
        return list;
    }

    public ArrayList<NoticeDto> selectSearchDate(SearchDate searchDate,Pageable pageable){
        List<NoticeEntity> entities = noticeQueryRepository.findBySearchDate(searchDate, pageable);
        ArrayList<NoticeDto> list = new ArrayList<>();
        for(NoticeEntity entity : entities) {
            list.add(entity.toDto());
        }
        return list;
    }

}
