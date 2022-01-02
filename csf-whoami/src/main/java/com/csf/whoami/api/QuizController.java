/**
 * Functions list:
 * - Create quiz test.
 * - Get quiz test.
 * - Get test result.
 * - Invite friend.
 * - Share quiz.
 */
package com.csf.whoami.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csf.base.domain.quiz.QuizAnswersDomain;
import com.csf.base.domain.quiz.QuizRequestDomain;
import com.csf.base.domain.quiz.ResultResponseDomain;
import com.csf.whoami.service.QuizService;
import com.csf.whoami.service.TemplateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/w/api/v1/quiz")
@Api
public class QuizController {

	@Autowired
	private QuizService quizTestService;
	@Autowired
	private TemplateService templateService;

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

	@GetMapping("/quizTmp")
	public String getTempQuestion(@RequestParam(value = "questionType") String questionType) {
		return templateService.tempQuestion(questionType);
	}
}
