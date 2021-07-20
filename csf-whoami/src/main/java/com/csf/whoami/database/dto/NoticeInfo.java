package com.csf.whoami.database.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NoticeInfo {

    @JsonInclude(Include.NON_NULL)
    private String id;
    @JsonInclude(Include.NON_NULL)
    private String adminId;
    private String userName;
    private String title;
    private String content;
    private String isImportant;
    @JsonInclude(Include.NON_NULL)
    private String filePath;
    @JsonInclude(Include.NON_NULL)
    private String startAt;
    @JsonInclude(Include.NON_NULL)
    private String finishAt;
    @JsonInclude(Include.NON_NULL)
    private String createdAt;

    public NoticeInfo(Long noticeId, Long adminId, String title, String content, String important, String filePath, 
            Date startAt, Date finishAt, Date createdAt) {
//            , String adminName) {
//        id = StringsUtil.fromLong(noticeId);
//        this.adminId = StringsUtil.fromLong(adminId);
        this.title = title;
        this.content = content;
        this.isImportant = important;
        this.filePath = filePath;
//        this.startAt = DateUtil.convertDateToString(startAt, DateUtil.DATE_CREATE_FORMAT);
//        this.finishAt = DateUtil.convertDateToString(finishAt, DateUtil.DATE_CREATE_FORMAT);
//        this.createdAt = DateUtil.convertDateToString(createdAt, DateUtil.DATE_CREATE_FORMAT);
//        this.userName = adminName;
    }
}
