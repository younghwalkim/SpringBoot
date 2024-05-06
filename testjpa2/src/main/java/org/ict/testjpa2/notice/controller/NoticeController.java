package org.ict.testjpa2.notice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ict.testjpa2.common.SearchDate;
import org.ict.testjpa2.notice.model.dto.NoticeDto;
import org.ict.testjpa2.notice.model.service.NoticeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@CrossOrigin    // 리엑트 어플리케이션(포트다름)의 자원 요청을 처리하기 위함.
@RestController
@RequiredArgsConstructor
@RequestMapping("/notices")
public class NoticeController {

    // 의존성 주입
    private final NoticeService noticeService;
    private final SearchDate searchDate;

    // 목록
    @GetMapping("/list")
    public ResponseEntity<List<NoticeDto>> selectList(
            @RequestParam(name="page") int page,
            @RequestParam(name="limit") int limit){

        log.info("/boards/list : " + page + ", " + limit);

        //JPA 가 제공하는 Pageable 객체를 사용함
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "noticeNo"));

        //페이지에 출력할 목록 조회해 옴    => 응답 처리

        /* 목록값 리턴 */
        return new ResponseEntity<>(noticeService.selectList(pageable), HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<List<NoticeDto>> selectSearchDate(
            @RequestParam(name="begin") java.sql.Date begin,
            @RequestParam(name="end") java.sql.Date end,
            @RequestParam(name="page") int page,
            @RequestParam(name="limit") int limit){

        log.info("### notice selectList()");

        //JPA 가 제공하는 Pageable 객체를 사용함
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "noticeNo"));

        //페이지에 출력할 목록 조회해 옴    => 응답 처리
        return new ResponseEntity<>(noticeService.selectSearchDate(searchDate, pageable), HttpStatus.OK);
    }

    // 상세조회
    @GetMapping("/{num}")
    public ResponseEntity<NoticeDto> selectNoticeDetail(
            @PathVariable("num") int noticeNum){

        log.info("### /boards/" + noticeNum + "요청");

        /* 컨트롤러에서 서비스 호출 */
        return new ResponseEntity<>(noticeService.selectNotice(noticeNum),HttpStatus.OK);
    }


    // top3
    @GetMapping("/ntop3")
    public ResponseEntity<List<NoticeDto>> selectNewTop3(){
        log.info("### /boards/ntop3 : selectNewTop3()");

        /* 목록값 리턴 */
        return new ResponseEntity<>(noticeService.selectTop3(), HttpStatus.OK);
    }
}
