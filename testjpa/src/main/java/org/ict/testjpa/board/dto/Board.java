package org.ict.testjpa.board.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;


@Data
@Entity	/* jpa 에서는 dto 클래스를 반드시 Entity 로 등록해야 함. */
@Table(name="board")
@SequenceGenerator(
		name="board_seq_bnum",
		sequenceName = "seq_bnum",
		initialValue = 157,
		allocationSize = 1)
public class Board {
	@Id	/* board 테이블의 primary key 와 연결 매핑되는 property 에 지정해야 함 (반드시) */
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "board_seq_bnum")

	@Column(name="BOARD_NUM")
	private Long boardNum;

	@Column(name="BOARD_WRITER")
	private String boardWriter;

	@Column(name="BOARD_TITLE")
	private String boardTitle;

	@Column(name="BOARD_DATE")
	private Date boardDate;

	@Column(name="BOARD_CONTENT")
	private String boardContent;

	/*
	@Column(name="BOARD_READCOUNT")
	private int boardReadCount;
	*/
}
