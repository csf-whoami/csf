/**
 * 
 */
package com.csf.base.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author mba0051
 *
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class InviteGroupDetailDomain {

	private String inviteId;
	private String groupId;
	private String groupName;
	private String exprireDate;

	/**
	 * @return the inviteId
	 */
	public String getInviteId() {
		return inviteId;
	}

	/**
	 * @param inviteId the inviteId to set
	 */
	public void setInviteId(String inviteId) {
		this.inviteId = inviteId;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the exprireDate
	 */
	public String getExprireDate() {
		return exprireDate;
	}

	/**
	 * @param exprireDate the exprireDate to set
	 */
	public void setExprireDate(String exprireDate) {
		this.exprireDate = exprireDate;
	}

	/**
	 * @param inviteId
	 * @param groupId
	 * @param groupName
	 * @param exprireDate
	 */
	public InviteGroupDetailDomain(String inviteId, String groupId, String groupName, String exprireDate) {
		this.inviteId = inviteId;
		this.groupId = groupId;
		this.groupName = groupName;
		this.exprireDate = exprireDate;
	}

	/**
	 * 
	 */
	public InviteGroupDetailDomain() {
		super();
		// TODO Auto-generated constructor stub
	}
}
