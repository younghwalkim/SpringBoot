package org.ict.testjpa2.notice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ict.testjpa2.notice.model.dto.NoticeDto;
import org.ict.testjpa2.notice.model.service.NoticeService;
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

    // 목록
    @GetMapping()
    public ResponseEntity<List<NoticeDto>> selectList(){
        log.info("### notice selectList()");

        /* 목록값 리턴 */
        return new ResponseEntity<>(noticeService.selectList(), HttpStatus.OK);
    }

    // 상세조회
    @GetMapping("/{num}")
    public ResponseEntity<NoticeDto> selectNoticeDetail(
            @PathVariable("num") int noticeNum){
        log.info("### selectOne()");

        /* 컨트롤러에서 서비스 호출 */
        return new ResponseEntity<>(noticeService.selectNotice(noticeNum),HttpStatus.OK);
    }

    @GetMapping("/ntop3")
    public ResponseEntity<List<NoticeDto>> selectTop3(){
        log.info("### notice selectTop3()");

        /* 목록값 리턴 */
        return new ResponseEntity<>(noticeService.selectTop3(), HttpStatus.OK);
    }
}
