package com.csf.base.domain.response;

import java.util.List;

import com.csf.base.domain.RoleInfo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthenticationInfo {

	private String userId;
    private String loginName;
    private String token;
    private List<RoleInfo> roles;
}
