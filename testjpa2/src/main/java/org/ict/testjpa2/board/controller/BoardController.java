package org.ict.testjpa2.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ict.testjpa2.board.model.dto.BoardDto;
import org.ict.testjpa2.board.model.service.BoardService;
import org.ict.testjpa2.board.model.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin    // 리엑트 어플리케이션(포트다름)의 자원 요청을 처리하기 위함.
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    // 의존성 주입
    private final BoardService boardService;
    private final ReplyService replyService;

    @GetMapping()
    public ResponseEntity<List<BoardDto>> selectList(){
        log.info("### selectList()");

        /* 목록값 리턴 */
        return new ResponseEntity<>(boardService.selectList(), HttpStatus.OK);
    }

    @GetMapping("/btop3")
    public ResponseEntity<List<BoardDto>> selectTop3(){
        log.info("### selectTop3()");

        /* 목록값 리턴 */
        return new ResponseEntity<>(boardService.selectTop3(), HttpStatus.OK);
    }
}
