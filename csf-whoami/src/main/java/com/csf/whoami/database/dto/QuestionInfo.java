package com.csf.whoami.database.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.csf.base.utilities.DateTimeUtils;
import com.csf.base.utilities.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionInfo extends QuestionManagementInfo implements BaseInfo {

    private String groupId;
    private String groupName;
    private String channelId;
    private String channelName;
    private String content;

    private List<AnswerOption> options = new ArrayList<AnswerOption>();

    public QuestionInfo(Long questionId, Long questionTypeId, String typeName, Long groupId, String groupName,
            Long channelId, String channelName, String content, Date createdAt) {
        this.id = StringUtils.fromLong(questionId);
        this.questionTypeId = StringUtils.fromLong(questionTypeId);
        this.questionType = typeName;
        this.groupId = StringUtils.fromLong(groupId);
        this.groupName = groupName;
        this.channelId = StringUtils.fromLong(channelId);
        this.channelName = channelName;
        this.content = content;
        this.createdAt = DateTimeUtils.toString(createdAt, DateTimeUtils.YYYYMMDDhhmmss);
    }
}
