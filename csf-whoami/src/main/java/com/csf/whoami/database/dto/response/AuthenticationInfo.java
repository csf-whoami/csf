package com.csf.whoami.database.dto.response;

import java.util.List;

import com.csf.whoami.database.dto.RoleInfo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthenticationInfo {

	private String loginName;
	private String token;
	private List<RoleInfo> roles;
}
