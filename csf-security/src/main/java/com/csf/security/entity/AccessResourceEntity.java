/**
 * 
 */
package com.csf.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author mba0019
 *
 */
@Entity
@Table(name = "`tbl_access_management`")
public class AccessResourceEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5384187607166761850L;

	@Id
	@Column(name = "URL_ACCESS")
	private String accessUrl;
	@Column(name = "ROLE_CODE")
	private String roleCode;
	@Column(name = "ROLE_NAME")
	private String roleName;

	/**
	 * @return the accessUrl
	 */
	public String getAccessUrl() {
		return accessUrl;
	}

	/**
	 * @param accessUrl the accessUrl to set
	 */
	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	/**
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * @param roleCode the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
