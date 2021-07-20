package com.csf.whoami.service;

import com.csf.whoami.database.dto.quiz.QuizResponseDomain;

/**
 * @author mba0051
 *
 */
public interface ExaminationsService {

	QuizResponseDomain startExamination(String quizId);
}
