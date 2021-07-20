package com.csf.whoami.database.dto.response;

import java.util.Date;
import java.util.List;

import com.csf.base.utilities.DateTimeUtils;
import com.csf.base.utilities.StringUtils;
import com.csf.whoami.database.dto.RoleInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UserInfo {

    @JsonInclude(Include.NON_NULL)
    private String id;
    private String username;
    @JsonIgnore
    private String userId;
    @JsonInclude(Include.NON_NULL)
    private String activedAt;
    @JsonIgnore
    private String startedAt;
    @JsonIgnore
    private String finishedAt;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    @JsonInclude(Include.NON_NULL)
    private String createDate;
    @JsonInclude(Include.NON_NULL)
    private String usable;
    @JsonInclude(Include.NON_NULL)
    private String description;

    List<RoleInfo> roles;
    @JsonInclude(Include.NON_NULL)
    private String roleId;
    @JsonInclude(Include.NON_NULL)
    private String roleName;

    public UserInfo(Long id, String fullName, String email, Date createdAt, Date activedAt, Long roleId, String roleName) {
        this.id = StringUtils.fromLong(id);
        this.fullName = fullName;
        this.email = email;
        this.createDate = DateTimeUtils.toString(createdAt, DateTimeUtils.YYYYMMDD);
        this.roleId = StringUtils.fromLong(roleId);
        this.roleName = roleName;
        this.activedAt = DateTimeUtils.toString(activedAt, DateTimeUtils.YYYYMMDD);
        this.usable = activedAt == null ? "N" : "Y";
    }
}
