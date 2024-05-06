package org.ict.testjpa2.board.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ict.testjpa2.board.model.dto.BoardDto;

import java.util.*;

// board.jpa.entity.BoardEntity.java : board 테이블에 대한 가이드 클래스

@Data
@Entity     // JPA가 관리함, 테이블과 DTO(VO) 클래스 매팅시 반드시 필요함.
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="BOARD")    // 매핑할 테이블 이름 지정함, board 테이블을 자동으로 만들어주기도 함.
public class BoardEntity {

    /* @ID : jpa가 객체를 관리할 때 식별할 기본키 저장 */
    /*       board 테이블의 primary key 와 연결 매핑되는 property 에 지정해야 함 (반드시) */
    @Id
    /* 사용시 무조건 default 로 입력되므로 주석처리 할 것. */
    // @GeneratedValue(strategy = GenerationType.IDENTITY)  // primary key 지정
    @Column(name="BOARD_NUM", nullable = false)
    private int boardNum;

    @Column(name="BOARD_WRITER", nullable = false, length = 50)
    private String boardWriter;

    @Column(name="BOARD_TITLE", nullable = false, length = 50)
    private String boardTitle;

    @Column(name="BOARD_CONTENT", nullable = false, length = 2000)
    private String boardContent;

    @Column(name="BOARD_ORIGINAL_FILENAME", length = 100)
    private String boardOriginalFilename;

    @Column(name="BOARD_RENAME_FILENAME", length = 100)
    private String boardRenameFilename;

    @Column(name="BOARD_READCOUNT")
    private int boardReadCount;

    @Column(name="BOARD_DATE")
    private java.util.Date boardDate;

    @PrePersist   //jpa 로 가기 전에 작동됨
    public void prePersist(){
        //boardDate 에 현재 날짜 적용
        boardDate = new Date(System.currentTimeMillis());
    }

    // entity --> dto 로 변환하는 메소드 추가함
    public BoardDto toDto(){
        return BoardDto.builder()
            .boardNum(this.boardNum)
            .boardWriter(this.boardWriter)
            .boardTitle(this.boardTitle)
            .boardContent(this.boardContent)
            .boardOriginalFilename(this.boardOriginalFilename)
            .boardRenameFilename(this.boardRenameFilename)
            .boardReadCount(this.boardReadCount)
            .boardDate(this.boardDate)
            .build();
    }

    /* 필요없음.
    // entity --> dto 로 변환하는 메소드 추가함
    public BoardDto toDtoTop3(){
        return BoardDto.builder()
                .boardNum(this.boardNum)
                .boardTitle(this.boardTitle)
                .boardReadCount(this.boardReadCount)
                .build();
    }
    */

}
