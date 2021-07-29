/**
 * 
 */
package com.csf.base.domain.quiz;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author mba0051
 *
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class QuizAnswersDomain {

	private String quizId;
	private String startTime;
	private String endTime;
	private List<AnswerRequestDomain> answers;

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
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the answers
	 */
	public List<AnswerRequestDomain> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(List<AnswerRequestDomain> answers) {
		this.answers = answers;
	}
}
