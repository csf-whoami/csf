/**
 * 
 */
package com.csf.base.domain.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author tuan
 *
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class QuestionTextDomain extends QuestionDomain {

	private String isMultiLine;
	private String correctAnswer;

	/**
	 * @return the isMultiLine
	 */
	public String getIsMultiLine() {
		return isMultiLine;
	}

	/**
	 * @param isMultiLine the isMultiLine to set
	 */
	public void setIsMultiLine(String isMultiLine) {
		this.isMultiLine = isMultiLine;
	}

	/**
	 * @return the correctAnswer
	 */
	@JsonIgnore
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * @param correctAnswer the correctAnswer to set
	 */
	@JsonProperty
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	/**
	 * 
	 */
	public QuestionTextDomain() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param questionId
	 * @param questionType
	 * @param questionContent
	 * @param groupId
	 * @param isMultiLine
	 * @param correctAnswer
	 */
	public QuestionTextDomain(Long questionId, String questionType, String questionContent, String groupId,
			String isMultiLine, String correctAnswer) {
//		super(questionId, questionType, questionContent, groupId);
		this.isMultiLine = isMultiLine;
		this.correctAnswer = correctAnswer;
	}
}