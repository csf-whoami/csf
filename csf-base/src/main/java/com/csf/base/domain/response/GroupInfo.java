package com.csf.base.domain.response;

import java.util.Date;

//import org.springframework.data.domain.Page;

import com.csf.base.utilities.DateTimeUtils;
import com.csf.base.utilities.StringUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GroupInfo implements BaseInfo {

    @JsonInclude(Include.NON_NULL)
    private String id;
//    @Length(min = 3, max = 64)
    private String name;
//    @Length(min = 3, max = 64)
    private String groupUrl;
    @JsonInclude(Include.NON_NULL)
//    @Length(min = 3, max = 20)
    private String typeId;
    private String usable;
    private String isPrivate;
    private String isPublish;
    @JsonInclude(Include.NON_NULL)
    private String isLock;
    @JsonInclude(Include.NON_NULL)
    private String isClosed;
    private String description;
    private String createDate;

//    private Page<ChannelInfo> channels;

    public GroupInfo(Long id, String groupName, String groupType, String isPrivate, String isPublish, String isLock, Date createDate) {
        this.id = StringUtils.fromLong(id);
        this.name = groupName;
        this.typeId = groupType;
        this.isPrivate = isPrivate;
        this.isPublish = isPublish;
        this.isLock = isLock;
        this.createDate = DateTimeUtils.convertDateToString(createDate, DateTimeUtils.YYYYMMDD);
    }
}
