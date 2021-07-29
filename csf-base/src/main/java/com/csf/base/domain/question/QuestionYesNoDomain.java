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
public class QuestionYesNoDomain extends QuestionDomain {

	private String correctAnswer;

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
	public QuestionYesNoDomain() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param questionId
	 * @param questionType
	 * @param questionContent
	 * @param groupId
	 * @param correctAnswer
	 */
	public QuestionYesNoDomain(Long questionId, String questionType, String questionContent, String groupId,
			String correctAnswer) {
//		super(questionId, questionType, questionContent, groupId);
		this.correctAnswer = correctAnswer;
	}
}
