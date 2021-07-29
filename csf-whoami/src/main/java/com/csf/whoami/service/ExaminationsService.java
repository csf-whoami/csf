package com.csf.whoami.service;

import com.csf.base.domain.quiz.QuizResponseDomain;

/**
 * @author mba0051
 *
 */
public interface ExaminationsService {

	QuizResponseDomain startExamination(String quizId);
}
