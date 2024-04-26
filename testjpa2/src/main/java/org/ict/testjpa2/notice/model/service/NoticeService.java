package org.ict.testjpa2.notice.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ict.testjpa2.board.jpa.entity.BoardEntity;
import org.ict.testjpa2.board.model.dto.BoardDto;
import org.ict.testjpa2.notice.jpa.entity.NoticeEntity;
import org.ict.testjpa2.notice.jpa.repository.NoticeRepository;
import org.ict.testjpa2.notice.model.dto.NoticeDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public ArrayList<NoticeDto> selectTop3(){
        List<NoticeEntity> entityList = noticeRepository.findTop3();
        ArrayList<NoticeDto> list = new ArrayList<>();

        // 내림차순정렬된 상위 3개만 추출함
        // JPQL은 where, group by 절에서만 서브쿼리 사용 가능함. => from 절에서는 서브쿼리 사용 못 함.
        for(int i = 0; i < 3; i++){
            NoticeEntity entity = entityList.get(i);
            NoticeDto dto = entity.toDtoTop3();

            list.add(dto);
        }

        return list;
    }

    public ArrayList<NoticeDto> selectList(){
        List<NoticeEntity> entityList = noticeRepository.findAll(Sort.by(Sort.Direction.DESC,"noticeno"));
        ArrayList<NoticeDto> list = new ArrayList<>();

        for(int i = 0; i < entityList.size(); i++){
            NoticeEntity entity = entityList.get(i);
            NoticeDto dto = entity.toDto();

            list.add(dto);
        }

        return list;
    }
}
