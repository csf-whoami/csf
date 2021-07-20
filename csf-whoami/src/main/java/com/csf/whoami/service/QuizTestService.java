/**
 * 
 */
package com.csf.whoami.service;

import com.csf.whoami.database.dto.quiz.QuizAnswersDomain;
import com.csf.whoami.database.dto.quiz.QuizRequestDomain;
import com.csf.whoami.database.dto.quiz.ResultResponseDomain;

public interface QuizTestService {

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
