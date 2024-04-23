package org.ict.testjpa.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ict.testjpa.board.dto.Board;
import org.ict.testjpa.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private final BoardService service;

    // 목록
    @GetMapping
    public ResponseEntity<List<Board>> selectAll(){
        log.info("selectAll()");

        /* 목록값 리턴 */
        return new ResponseEntity<>(service.selectList(), HttpStatus.OK);
    }

    // 상세조회
    @GetMapping("/{boardNum}")
    public ResponseEntity<Board> selectOne(
            @PathVariable("boardNum") long boardNum){
        log.info("selectOne()");

        /* 해당 게시물 DB 조회 */
        Optional<Board> optionalBoard = service.selectBoard(boardNum);
        /* 해당 게시물 데이터 리턴 */
        return new ResponseEntity<>(optionalBoard.get(),HttpStatus.OK);
    }

    // 등록
    @PostMapping
    public ResponseEntity<Board> insert(@RequestBody Board board){
        log.info("insert");

        /* 등록일자 담기 */
        board.setBoardDate((new Date(Calendar.getInstance().getTimeInMillis())));
        /* 등록처리 */
        service.insertBoard(board);
        /* board 값 리턴 */
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    // 수정
    @PutMapping
    public ResponseEntity<Board> update(@RequestBody Board board){
        log.info("update");
        
        /* 수정일자 담기 */
        board.setBoardDate((new Date(Calendar.getInstance().getTimeInMillis())));
        /* 수정처리 */        
        service.updateBoard(board);
        /* board 값 리턴 */        
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/{boardNum}")
    public ResponseEntity<Void> delete(@PathVariable("boardNum") long boardnum){
        log.info("delete");

        /* 삭제처리 */        
        service.deleteBoard(boardnum);
        /* 리턴값 없음 */
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
