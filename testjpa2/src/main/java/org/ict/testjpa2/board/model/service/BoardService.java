package org.ict.testjpa2.board.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ict.testjpa2.board.jpa.entity.BoardEntity;
import org.ict.testjpa2.board.jpa.repository.BoardNativeVo;
import org.ict.testjpa2.board.jpa.repository.BoardRepository;
import org.ict.testjpa2.board.model.dto.BoardDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    // Serivce 에 대한 interface  만들어서  상속받은 ServiceImpl 클래스를 만드는 구조로 작성해도 됨.

    private final BoardRepository boardRepository;
    private final BoardDto boardDto;

    // Top3 목록
    public ArrayList<BoardDto> selectTop3(){
        List<BoardNativeVo> nativeVoList = boardRepository.findTop3();
        ArrayList<BoardDto> list = new ArrayList<>();

        // 내림차순정렬된 상위 3개만 추출함
        // JPQL은 where, group by 절에서만 서브쿼리 사용 가능함. => from 절에서는 서브쿼리 사용 못 함.
        for(int i = 0; i < 3; i++){
            BoardDto boardDto = new BoardDto();

            boardDto.setBoardNum(nativeVoList.get(i).getBoard_num());
            boardDto.setBoardTitle(nativeVoList.get(i).getBoard_title());
            boardDto.setBoardReadCount(nativeVoList.get(i).getBoard_readcount());

            list.add(boardDto);
        }

        return list;
    }
    
    // 목록
    public ArrayList<BoardDto> selectList(){
        List<BoardEntity> entityList = boardRepository.findAll(Sort.by(Sort.Direction.DESC,"boardNum"));
        ArrayList<BoardDto> list = new ArrayList<>();

        for(int i = 0; i < entityList.size(); i++){
            BoardEntity entity = entityList.get(i);
            BoardDto dto = entity.toDto();

            list.add(dto);
        }

        return list;
    }

    // 상세조회
    public BoardDto selectBoard(int boardNum) {
        /* Repository -> Entity : jpa 활용하여 데이터 조회 부분 */
        Optional<BoardEntity> optionalBoardEntity =  boardRepository.findById(boardNum);
        /* Entity --> Dto 전환 */
        BoardEntity boardEntity = optionalBoardEntity.get();

        /* 조회수 변수에 값 담기 */
        boardEntity.setBoardReadCount(boardEntity.getBoardReadCount()+1);

        /* 저장 */
        boardRepository.save(boardEntity);
        return boardEntity.toDto();
    }

    // 수정
    public void updateBoard(BoardDto boardDto) {

        /* 기존데이터 가져오기 - 조회수 증가 및 save 작동 */
        BoardEntity boardEntity = boardRepository.getReferenceById(boardDto.getBoardNum());

        /* 글 수정 대상 변수에 값 담기 */
        boardEntity.setBoardTitle(boardDto.getBoardTitle());
        boardEntity.setBoardContent(boardDto.getBoardContent());

        /* 저장  */
        boardRepository.save(boardEntity);
    }

    // 삭제
    public void deleteBoard(int boardnum){
        log.info("### delete");

        /* 삭제처리 */
        boardRepository.deleteById(boardnum);
    }

    // 등록
    public void insertBoard(BoardDto boardDto) {
        // 데이터베이스 시퀀스 객체를 사용하지 않는 경우, 마지막 번호 가져오기 처리 필요

        /* 마지막 번호 가져오기 */
        boardDto.setBoardNum(boardRepository.findLastBoardNum() + 1);

        /* insert 처리 */
        boardRepository.save(boardDto.toEntity());
    }
}
