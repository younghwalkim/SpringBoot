package org.ict.testjpa2.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ict.testjpa2.board.model.dto.BoardDto;
import org.ict.testjpa2.board.model.service.BoardService;
import org.ict.testjpa2.board.model.service.ReplyService;
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
@RequestMapping("/boards")
public class BoardController {

    // 의존성 주입
    private final BoardService boardService;
    private final ReplyService replyService;

    // 조회 Top 3
    @GetMapping("/btop3")
    public ResponseEntity<List<BoardDto>> selectTop3(){
        log.info("### /boards/btop3 => selectTop3() ");

        // 3개 리턴
        return new ResponseEntity<>(boardService.selectTop3(), HttpStatus.OK);
    }

    // 목록
    @GetMapping("/list")
    public ResponseEntity<List<BoardDto>> selectList(
            @RequestParam(name="page") int page,
            @RequestParam(name="limit") int limit){
        log.info("### /boards/list : " + page + ", " + limit);

        //JPA 가 제공하는 Pageable 객체를 사용함
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "boardNum"));

        /* 목록값 리턴 */
        return new ResponseEntity<>(boardService.selectList(pageable), HttpStatus.OK);
    }

    // 상세조회
    @GetMapping("/{boardNum}")
    public ResponseEntity<BoardDto> selectBoardDetail(
            @PathVariable("boardNum") int boardNum){
        log.info("### selectOne()");

        /* 컨트롤러에서 서비스 호출 */
        return new ResponseEntity<>(boardService.selectBoard(boardNum),HttpStatus.OK);
    }

    // 등록
    @PostMapping
    public ResponseEntity<BoardDto> insertBoard(@RequestBody BoardDto boardDto){
        log.info("### insert");
        /* 등록일자 담기 */
        // BoardDto.setBoardDate((new Date(Calendar.getInstance().getTimeInMillis())));

        /* 등록처리 */
        boardService.insertBoard(boardDto);

        /* 리턴 */
        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    // 수정
    @PutMapping("/{boardNum}")
    public ResponseEntity<BoardDto> updateBoard(@RequestBody BoardDto boardDto){
        log.info("### update");

        /* 수정일자 담기 */
        boardDto.setBoardDate((new Date(Calendar.getInstance().getTimeInMillis())));
        /* 수정처리 */
        boardService.updateBoard(boardDto);
        /* board 값 리턴 */
        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/{boardNum}")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable("boardNum") int boardnum){
        log.info("### delete");

        /* 삭제처리 */
        boardService.deleteBoard(boardnum);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
