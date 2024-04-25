package org.ict.testjpa2.board.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.ict.testjpa2.board.jpa.entity.BoardEntity;
import org.springframework.stereotype.Component;

@Data   //@ToString, @Getter, @Setter,...
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    // 게시글 원글
    // 프로퍼티(propety, == 맴버변수, filed)
    private int boardNum;
    private String boardWriter;
    private String boardTitle;
    private String boardContent;
    private String boardOriginalFilename;
    private String boardRenameFilename;
    private int boardReadCount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.util.Date boardDate;

    // dto--> entity 로 변환하는 메소드 추가함
    public BoardEntity toEntity(){
        return BoardEntity.builder()
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
}
