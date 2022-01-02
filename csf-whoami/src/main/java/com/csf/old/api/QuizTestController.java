/**
 * Functions list:
 * - Create quiz test.
 * - Get quiz test.
 * - Get test result.
 * - Invite friend.
 * - Share quiz.
 */
package com.csf.old.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csf.base.domain.quiz.QuizAnswersDomain;
import com.csf.base.domain.quiz.QuizRequestDomain;
import com.csf.base.domain.quiz.ResultResponseDomain;
import com.csf.whoami.service.QuizService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("quizs")
@Api
public class QuizTestController {

	@Autowired
	private QuizService quizTestService;

	/**
	 * Generate random quiztest by group.
	 * 
	 * @author mba0051
	 * @param domain
	 * @return
	 */
	@ApiOperation(value = "Phương thức tạo bài thi.")
	@PostMapping
	public String makeQuizlet(@RequestBody QuizRequestDomain domain) {
		return quizTestService.generateRandomQuiz(domain);
	}

	/**
	 * Calculate quiz test result.
	 * 
	 * @author mba0051
	 * @param domain
	 * @return
	 */
	@ApiOperation(value = "Phương thức chấm bài thi.")
	@PostMapping("/{quizId}")
	public ResultResponseDomain calculateTestResult(@RequestBody QuizAnswersDomain domain) {
		ResultResponseDomain response = quizTestService.calculateExaminationResult(domain);
		return response;
	}
}
