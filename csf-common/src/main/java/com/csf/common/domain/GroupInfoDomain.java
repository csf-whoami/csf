/**
 * 
 */
package com.csf.security.domain;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author tuan
 *
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GroupInfoDomain {

	private String groupId;
	private String groupName;
	private String groupUrl;
	private String groupImg;
	private List<ChannelInfoDomain> channels;

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

	/**
	 * @return the groupImg
	 */
	public String getGroupImg() {
		return groupImg;
	}

	/**
	 * @param groupImg the groupImg to set
	 */
	public void setGroupImg(String groupImg) {
		this.groupImg = groupImg;
	}

	/**
	 * @return the channels
	 */
	public List<ChannelInfoDomain> getChannels() {
		return channels;
	}

	/**
	 * @param channels the channels to set
	 */
	public void setChannels(List<ChannelInfoDomain> channels) {
		this.channels = channels;
	}
}
