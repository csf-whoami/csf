package com.csf.base.domain.response;

import java.util.Date;

import com.csf.base.domain.enumtype.YesNoEnum;
import com.csf.base.utilities.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tuan
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelInfo implements BaseInfo {

    @JsonInclude(Include.NON_NULL)
    private String id;
    private String name;
    private String url;
    @JsonInclude(Include.NON_NULL)
    private String description;
    @JsonInclude(Include.NON_NULL)
    private String createDate;
    @JsonInclude(Include.NON_NULL)
    private String lockStatus;
    private String closedStatus;
    private String privateStatus;
    private String groupId;
    private String groupName;

    public ChannelInfo(Long channelId, String channelName, YesNoEnum lockStatus, Date createDate) {
        this.id = String.valueOf(channelId);
        this.name = channelName;
        this.createDate = DateTimeUtils.convertDateToString(createDate, DateTimeUtils.YYYYMMDD);
        this.lockStatus = lockStatus.toString();
    }

    public ChannelInfo(Long channelId, String channelName, String channelUrl, YesNoEnum lockStatus, Date createDate) {
        this.id = String.valueOf(channelId);
        this.name = channelName;
        this.url = channelUrl;
        this.lockStatus = lockStatus.toString();
        this.createDate = DateTimeUtils.convertDateToString(createDate, DateTimeUtils.YYYYMMDD);
    }
}
