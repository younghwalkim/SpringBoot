package org.ict.testjpa2.board.model.dto;

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
public class ReplyDto {
    private int replynum;
    private String replyWriter;
    private String replyTitle;
    private String replyContent;
    private int boardRef;
    private int replyReplyRef;
    private int replyLev;
    private int replySeq;
    private int replyReadcount;
    private java.util.Date replyDate;
}
