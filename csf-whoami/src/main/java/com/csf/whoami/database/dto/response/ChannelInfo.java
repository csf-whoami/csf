package com.csf.whoami.database.dto.response;

import java.util.Date;

import com.csf.base.utilities.DateTimeUtils;
import com.csf.whoami.database.dto.YesNo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tuan
 *
 */
@ApiModel(description = "Thông tin về kênh.")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelInfo implements BaseInfo {

    @JsonInclude(Include.NON_NULL)
    @ApiModelProperty(name = "id", required = true, position = 0, example = "7aaee0e2-6884-4fd7-ba63-21d76723dce2")
    private String id;
    @ApiModelProperty(name = "name", required = true, position = 1, example = "Tieng-Anh")
    private String channelName;
    private String channelUrl;
    @JsonInclude(Include.NON_NULL)
    @ApiModelProperty(name = "description", required = true, position = 2, example = "Kênh chuyên về học tiếng Anh.")
    private String channelDescription;
    @JsonInclude(Include.NON_NULL)
    private String createDate;
    @JsonInclude(Include.NON_NULL)
    @ApiModelProperty(name = "lock", required = true, position = 3, example = "Tình trạng khoá kênh.")
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
