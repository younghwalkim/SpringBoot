package org.ict.testjpa2.notice.jpa.entity;

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
@Table(name="NOTICE")    // 매핑할 테이블 이름 지정함, board 테이블을 자동으로 만들어주기도 함.
public class NoticeEntity {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="NOTICENO")
    private int noticeNo;

    @Column(name="NOTICETITLE")
    private String noticeTitle;

    @Column(name="NOTICEDATE")
    private java.sql.Date noticeDate;

    @Column(name="NOTICEWRITER")
    private String noticeWriter;

    @Column(name="NOTICECONTENT")
    private String noticeContent;

    @Column(name="ORIGINAL_FILEPATH")
    private String originalFilePath;

    @Column(name="RENAME_FILEPATH")
    private String renameFilePath;

    @Column(name="IMPORTANCE")
    private String importance;

    @Column(name="IMP_END_DATE")
    private java.sql.Date impEndDate;

    @Column(name="READCOUNT")
    private int readCount;
}
