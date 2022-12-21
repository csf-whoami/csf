/**
 * 
 */
package com.csf.common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author mba0051
 *
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GroupDomain {

	@JsonProperty("group_id")
	private String groupId;
	@JsonProperty("group_name")
//	@Length(min = 3, max = 64)
	private String groupName;
	@JsonProperty("group_url")
//	@Length(min = 3, max = 64)
	private String groupUrl;
//	@Length(min = 3, max = 20)
	@JsonProperty("group_type")
	private String groupType;

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
	 * @return the groupType
	 */
	public String getGroupType() {
		return groupType;
	}

	/**
	 * @param groupType the groupType to set
	 */
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	/**
	 * @return the groupUrl
	 */
	public String getGroupUrl() {
		return groupUrl;
	}

	/**
	 * @param groupUrl the groupUrl to set
	 */
	public void setGroupUrl(String groupUrl) {
		this.groupUrl = groupUrl;
	}
}
