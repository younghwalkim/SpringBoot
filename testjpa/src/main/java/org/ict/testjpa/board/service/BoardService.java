package org.ict.testjpa.board.service;

import org.ict.testjpa.board.dto.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    public List<Board> selectList();            // 게시글 목록
    public Optional<Board> selectBoard(Long boardNum); // 게시글 한 개 상세조회
    public void insertBoard(Board board);       // 게시글 등록
    public void updateBoard(Board board);       // 게시글 수정
    public void deleteBoard(Long boardNum);     // 게시글 삭제
}
