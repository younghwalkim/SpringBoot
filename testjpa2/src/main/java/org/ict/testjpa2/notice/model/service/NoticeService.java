package org.ict.testjpa2.notice.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ict.testjpa2.notice.jpa.entity.NoticeEntity;
import org.ict.testjpa2.notice.jpa.repository.NoticeNativeVo;
import org.ict.testjpa2.notice.jpa.repository.NoticeRepository;
import org.ict.testjpa2.notice.model.dto.NoticeDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public ArrayList<NoticeDto> selectTop3(){
        List<NoticeNativeVo> nativeVoList = noticeRepository.findTop3();
        ArrayList<NoticeDto> list = new ArrayList<>();

        // 내림차순정렬된 상위 3개만 추출함
        // JPQL은 where, group by 절에서만 서브쿼리 사용 가능함. => from 절에서는 서브쿼리 사용 못 함.
        for(int i = 0; i < 3; i++){
            NoticeDto noticeDto = new NoticeDto();

            noticeDto.setNoticeNo(nativeVoList.get(i).getNoticeNo());
            noticeDto.setNoticeTitle(nativeVoList.get(i).getNoticeTitle());
            noticeDto.setNoticeDate(nativeVoList.get(i).getNoticeDate());

            list.add(noticeDto);
        }

        return list;
    }

    // 목록
    public ArrayList<NoticeDto> selectList(){
        List<NoticeEntity> entityList = noticeRepository.findAll(Sort.by(Sort.Direction.DESC,"noticeNo"));
        ArrayList<NoticeDto> list = new ArrayList<>();

        for(int i = 0; i < entityList.size(); i++){
            NoticeEntity entity = entityList.get(i);
            NoticeDto dto = entity.toDto();

            list.add(dto);
        }

        return list;
    }

    // 상세조회
    public NoticeDto selectNotice(int noticeNum) {
        /* Repository -> Entity : jpa 활용하여 데이터 조회 부분 */
        Optional<NoticeEntity> optionalNoticeEntity = noticeRepository.findById(noticeNum);
        /* Entity --> Dto 전환 */
        NoticeEntity noticeEntity = optionalNoticeEntity.get();

        /* 조회수 변수에 값 담기 */
        noticeEntity.setReadCount(noticeEntity.getReadCount()+1);

        /* 저장 */
        noticeRepository.save(noticeEntity);

        return noticeEntity.toDto();
    }
}
