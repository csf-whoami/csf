package com.csf.base.domain;

import java.util.Date;

import com.csf.base.utilities.DateTimeUtils;
import com.csf.base.utilities.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionManagementInfo implements BaseInfo {

    protected String id;
    protected String questionTypeId;
    protected String questionType;
    protected String createdAt;

    public QuestionManagementInfo(Long id, String questionType, Date createdAt) {
        this.id = StringUtils.fromLong(id);
        this.questionType = questionType;
        this.createdAt = DateTimeUtils.convertDateToString(createdAt, DateTimeUtils.YYYYMMDDhhmmss);
    }
}
