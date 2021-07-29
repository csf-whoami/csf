package com.csf.base.domain;

import java.util.Date;

import com.csf.base.constant.CommonConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QnADetail {
	private long id;

    private String qnaType;

    private String userName;

    private String title;

    private String content;

    private String filePath;

    private String reply;

    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone= CommonConstants.TIMEZONE)
    private Date createdAt;
}
