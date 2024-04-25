package org.ict.testjpa2.board.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ict.testjpa2.board.jpa.entity.BoardEntity;
import org.ict.testjpa2.board.jpa.repository.BoardRepository;
import org.ict.testjpa2.board.model.dto.BoardDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    // Serivce 에 대한 interface  만들어서  상속받은 ServiceImpl 클래스를 만드는 구조로 작성해도 됨.

    private final BoardRepository boardRepository;

    public ArrayList<BoardDto> selectTop3(){
        List<BoardEntity> entityList = boardRepository.findTop3();
        ArrayList<BoardDto> list = new ArrayList<>();

        // 내림차순정렬된 상위 3개만 추출함
        // JPQL은 where, group by 절에서만 서브쿼리 사용 가능함. => from 절에서는 서브쿼리 사용 못 함.
        for(int i = 0; i < 3; i++){
            BoardEntity entity = entityList.get(i);
            BoardDto dto = entity.toDtoTop3();

            list.add(dto);
        }

        return list;
    }

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

}
