package org.ict.testjpa2.board.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity     // JPA가 관리함, 테이블과 DTO(VO) 클래스 매팅시 반드시 필요함.
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="REPLY")    // 매핑할 테이블 이름 지정함, board 테이블을 자동으로 만들어주기도 함.
public class ReplyEntity {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="REPLY_NUM")
    private int replynum;

    @Column(name="REPLY_WRITER")
    private String replyWriter;

    @Column(name="REPLY_TITLE")
    private String replyTitle;

    @Column(name="REPLY_CONTENT")
    private String replyContent;

    @Column(name="BOARD_REF")
    private int boardRef;

    @Column(name="REPLY_REPLY_REF")
    private int replyReplyRef;

    @Column(name="REPLY_LEV")
    private int replyLev;

    @Column(name="REPLY_SEQ")
    private int replySeq;

    @Column(name="REPLY_READCOUNT")
    private int replyReadcount;

    @Column(name="REPLY_DATE")
    private java.util.Date replyDate;
}
