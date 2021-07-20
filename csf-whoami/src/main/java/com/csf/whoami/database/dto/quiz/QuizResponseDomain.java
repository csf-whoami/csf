/**
 * 
 */
package com.csf.whoami.database.dto.quiz;

import java.util.List;

import com.csf.whoami.database.dto.question.QuestionDomain;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author mba0051
 *
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class QuizResponseDomain {

	private String quizId;
	private List<QuestionDomain> questions;
	private String timeStart;
	private String duration;

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
	 * @return the questions
	 */
	public List<QuestionDomain> getQuestions() {
		return questions;
	}

	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(List<QuestionDomain> questions) {
		this.questions = questions;
	}

	/**
	 * @return the timeStart
	 */
	public String getTimeStart() {
		return timeStart;
	}

	/**
	 * @param timeStart the timeStart to set
	 */
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @param quizId
	 * @param questions
	 * @param timeStart
	 * @param duration
	 */
	public QuizResponseDomain(String quizId, List<QuestionDomain> questions, String timeStart, String duration) {

		this.quizId = quizId;
		this.questions = questions;
		this.timeStart = timeStart;
		this.duration = duration;
	}

	/**
	 * 
	 */
	public QuizResponseDomain() {
		super();
		// TODO Auto-generated constructor stub
	}
}
