/**
 * 
 */
package com.csf.whoami.database.dto.quiz;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author mba0051
 *
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResultResponseDomain {

	private String markStatus;
	private String totalMark;
	private String message;

	/**
	 * @return the markStatus
	 */
	public String getMarkStatus() {
		return markStatus;
	}

	/**
	 * @param markStatus the markStatus to set
	 */
	public void setMarkStatus(String markStatus) {
		this.markStatus = markStatus;
	}

	/**
	 * @return the totalMark
	 */
	public String getTotalMark() {
		return totalMark;
	}

	/**
	 * @param totalMark the totalMark to set
	 */
	public void setTotalMark(String totalMark) {
		this.totalMark = totalMark;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
