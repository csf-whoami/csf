package com.csf.base.core.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;

public class AclParamVO {

	private Long id = 0L;
	private List<AclParam> aclParam;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<AclParam> getAclParam() {
		return aclParam;
	}
	public void setAclParam(List<AclParam> aclParam) {
		this.aclParam = aclParam;
	}

	public static class AclParam {
		private Permission permission;
		private Sid sid;
		private boolean granting = true;

		public Permission getPermission() {
			return permission;
		}
		public void setPermission(Permission permission) {
			this.permission = permission;
		}
		public Sid getSid() {
			return sid;
		}
		public void setSid(Sid sid) {
			this.sid = sid;
		}
		public boolean isGranting() {
			return granting;
		}
		public void setGranting(boolean granting) {
			this.granting = granting;
		}
		@Override
		public String toString() {
			return ReflectionToStringBuilder.toString(this);
		}
	}
}
