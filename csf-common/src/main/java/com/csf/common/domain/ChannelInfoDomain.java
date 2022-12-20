/**
 * 
 */
package com.csf.security.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author tuan
 *
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ChannelInfoDomain {
	private String channelId;
	private String channelName;
	private String channelDescription;
	private String createDate;
	private String lockStatus;

	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId the channelId to set
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return the channelName
	 */
	public String getChannelName() {
		return channelName;
	}

	/**
	 * @param channelName the channelName to set
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the lockStatus
	 */
	public String getLockStatus() {
		return lockStatus;
	}

	/**
	 * @param lockStatus the lockStatus to set
	 */
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	/**
	 * @return the channelDescription
	 */
	public String getChannelDescription() {
		return channelDescription;
	}

	/**
	 * @param channelDescription the channelDescription to set
	 */
	public void setChannelDescription(String channelDescription) {
		this.channelDescription = channelDescription;
	}

	/**
	 * 
	 */
	public ChannelInfoDomain() {
		super();
	}

	/**
	 * @param channelId
	 * @param channelName
	 * @param description
	 * @param createDate
	 * @param lockStatus
	 */
	public ChannelInfoDomain(String channelId, String channelName, String description, String createDate,
			String lockStatus) {
		super();
		this.channelId = channelId;
		this.channelName = channelName;
		this.channelDescription = description;
		this.createDate = createDate;
		this.lockStatus = lockStatus;
	}
}
