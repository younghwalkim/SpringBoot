package org.ict.testjpa2.notice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data   //@ToString, @Getter, @Setter,...
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {

    private int noticeNo;
    private String noticeTitle;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date noticeDate;

    private String noticeWriter;
    private String noticeContent;
    private String originalFilePath;
    private String renameFilePath;
    private String importance;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date impEndDate;

    private int readCount;

}
