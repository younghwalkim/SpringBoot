package org.ict.testjpa.notice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private java.sql.Date noticeDate;
	private String noticeWriter;
	private String noticeContent;
	private String originalFilePath;
	private String renameFilePath;
	private String importance;
	private java.sql.Date impEndDate;
	private int readCount;
}

