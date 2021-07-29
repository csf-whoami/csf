package com.csf.base.domain.response;

import java.util.Date;

import com.csf.base.domain.YesNo;
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
    private String channelName;
    private String channelUrl;
    @JsonInclude(Include.NON_NULL)
    private String channelDescription;
    @JsonInclude(Include.NON_NULL)
    private String createDate;
    @JsonInclude(Include.NON_NULL)
    private String lockStatus;
    private String closedStatus;
    private String privateStatus;
    private String groupId;
    private String groupName;

    public ChannelInfo(Long channelId, String channelName, YesNo lockStatus, Date createDate) {
        this.id = String.valueOf(channelId);
        this.channelName = channelName;
        this.createDate = DateTimeUtils.toString(createDate, DateTimeUtils.YYYYMMDD);
        this.lockStatus = lockStatus.toString();
    }

    public ChannelInfo(Long channelId, String channelName, String channelUrl, YesNo lockStatus, Date createDate) {
        this.id = String.valueOf(channelId);
        this.channelName = channelName;
        this.channelUrl = channelUrl;
        this.lockStatus = lockStatus.toString();
        this.createDate = DateTimeUtils.toString(createDate, DateTimeUtils.YYYYMMDD);
    }
}
