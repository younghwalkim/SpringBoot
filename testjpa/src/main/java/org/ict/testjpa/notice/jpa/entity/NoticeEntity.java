package org.ict.testjpa.notice.jpa.entity;

/*
   # board.jpa.entity.BoardEntity.java : board 테이블에 대한 가이드 클래스
    . BoardDto 의 property 와 board 테이블의 column 을 매칭함
	. @Column(name="컬럼명", nullable="true | false", length=크기) 등을 지정함
	. 컬럼에 대한 @Id, primary key, not null 등 제약조건 설정함
	. @Entity 선언함, @Table("테이블명")
	. 롬복의 @Data, @AllArgsConstructor, @NoArgsConstructor, @Builder 선언함
	. entity 를 dto 로 변환하는 메소드 추가함 : public BoardDto toDto()
	. 날짜 자료형을 LocalDateTime 으로 변경함
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.GregorianCalendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity     // JPA가 관리함, 테이블과 DTO(VO) 클래스 매팅시 반드시 필요함.
@Table(name="notice")   // 매핑할 테이블 이름 지정함, notice 테이블을 자동으로 만들어주기도 함.
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="NOTICENO")
    private int noticeNo;

    @Column(name="NOTICETITLE", nullable=false, length = 50)
    private String noticeTitle;

    @Column(name="NOTICEDATE", nullable = false)
    private java.util.Date noticeDate;

    @Column(name="NOTICEWRITER", nullable = false, length = 50)
    private String noticeWriter;

    @Column(name="NOTICECONTENT", nullable = false, length = 2000)
    private String noticeContent;

    @Column(name="ORIGINAL_FILEPATH")
    private String originalFilePath;

    @Column(name="RENAME_FILEPATH")
    private String renameFilePath;

    @Column(name="IMPORTANCE", nullable = false, columnDefinition = "N")
    private String importance;

    @Column(name="IMP_END_DATE")
    private java.util.Date impEndDate;

    @Column(name="READCOUNT", nullable = false, columnDefinition = "1")
    private int readCount;

    /* JPA로 넘아가기 전에 작동하라는 이노테이션임 */
    @PrePersist
    public void prePersist(){
        noticeDate = new GregorianCalendar().getGregorianChange();  // 현재 날짜, 시간 적용
    }
}
