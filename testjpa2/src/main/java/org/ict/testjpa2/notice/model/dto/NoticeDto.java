package org.ict.testjpa2.notice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ict.testjpa2.notice.jpa.entity.NoticeEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Data   //@ToString, @Getter, @Setter,...
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {

    private int noticeNo;
    private String noticeTitle;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date noticeDate;

    private String noticeWriter;
    private String noticeContent;
    private String originalFilePath;
    private String renameFilePath;
    private String importance;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date impEndDate;

    private int readCount;

    // dto--> entity 로 변환하는 메소드 추가함
    public NoticeEntity toEntity(){
        return NoticeEntity.builder()
                .noticeNo(noticeNo)
                .noticeTitle(noticeTitle)
                .noticeDate(noticeDate)
                .noticeWriter(noticeWriter)
                .noticeContent(noticeContent)
                .originalFilePath(originalFilePath)
                .renameFilePath(renameFilePath)
                .importance(importance)
                .impEndDate(impEndDate)
                .readCount(readCount)
                .build();
    }

}
