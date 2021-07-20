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
public class QuizRequestDomain {

	private String groupId;
	private String questionNumber;
	private String questionTime;
	private String includeRandomQuestion;
	private String quizTitle;

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
	 * @return the questionNumber
	 */
	public String getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * @param questionNumber the questionNumber to set
	 */
	public void setQuestionNumber(String questionNumber) {
		this.questionNumber = questionNumber;
	}

	/**
	 * @return the questionTime
	 */
	public String getQuestionTime() {
		return questionTime;
	}

	/**
	 * @param questionTime the questionTime to set
	 */
	public void setQuestionTime(String questionTime) {
		this.questionTime = questionTime;
	}

	/**
	 * @return the includeRandomQuestion
	 */
	public String getIncludeRandomQuestion() {
		return includeRandomQuestion;
	}

	/**
	 * @param includeRandomQuestion the includeRandomQuestion to set
	 */
	public void setIncludeRandomQuestion(String includeRandomQuestion) {
		this.includeRandomQuestion = includeRandomQuestion;
	}

	/**
	 * @return the quizTitle
	 */
	public String getQuizTitle() {
		return quizTitle;
	}

	/**
	 * @param quizTitle the quizTitle to set
	 */
	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}
}
