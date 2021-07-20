/**
 * 
 */
package com.csf.whoami.database.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author tuan
 *
 */
@ApiModel(description = "Thông tin về bài thi trong kênh.")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ChannelQuizDomain {

	@ApiModelProperty(name = "id", required = true, position = 0, example = "7aaee0e2-6884-4fd7-ba63-21d76723dce2")
	private String quizId;
	@ApiModelProperty(name = "name", required = true, position = 1, example = "Bài thi ngữ pháp")
	private String quizName;
	@ApiModelProperty(name = "user", required = true, position = 2, example = "7aaee0e2-6884-4fd7-ba63-2186h1f3dce2")
	private String quizUser;
	private String quizDateTime;

	/**
	 * @return the quizId
	 */
	public String getQuizId() {
		return quizId;
	}

	/**
	 * @param quizId the quizId to set
	 */
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}

	/**
	 * @return the quizName
	 */
	public String getQuizName() {
		return quizName;
	}

	/**
	 * @param quizName the quizName to set
	 */
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	/**
	 * @return the quizUser
	 */
	public String getQuizUser() {
		return quizUser;
	}

	/**
	 * @param quizUser the quizUser to set
	 */
	public void setQuizUser(String quizUser) {
		this.quizUser = quizUser;
	}

	/**
	 * @return the quizDateTime
	 */
	public String getQuizDateTime() {
		return quizDateTime;
	}

	/**
	 * @param quizDateTime the quizDateTime to set
	 */
	public void setQuizDateTime(String quizDateTime) {
		this.quizDateTime = quizDateTime;
	}

	/**
	 * @param quizId
	 * @param quizName
	 * @param quizUser
	 * @param quizDateTime
	 */
	public ChannelQuizDomain(String quizId, String quizName, String quizUser, String quizDateTime) {
		super();
		this.quizId = quizId;
		this.quizName = quizName;
		this.quizUser = quizUser;
		this.quizDateTime = quizDateTime;
	}

	/**
	 * 
	 */
	public ChannelQuizDomain() {
		super();
		// TODO Auto-generated constructor stub
	}

}
