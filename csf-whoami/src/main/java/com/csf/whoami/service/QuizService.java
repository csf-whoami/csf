/**
 * 
 */
package com.csf.whoami.service;

import com.csf.base.domain.quiz.QuizAnswersDomain;
import com.csf.base.domain.quiz.QuizRequestDomain;
import com.csf.base.domain.quiz.ResultResponseDomain;

public interface QuizService {

	/**
	 * Generate random quiztest with number question and time particular.
	 * @author mba0051
	 * @param domain
	 * @return
	 */
	String generateRandomQuiz(QuizRequestDomain domain);

	/**
	 * Calculate quiztest result.
	 * @author mba0051
	 * @param domain
	 * @return
	 */
	ResultResponseDomain calculateExaminationResult(QuizAnswersDomain domain);

}
