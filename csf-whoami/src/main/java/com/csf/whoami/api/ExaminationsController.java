/**
 * 
 */
package com.csf.whoami.api;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csf.base.domain.quiz.QuizResponseDomain;
import com.csf.whoami.service.ExaminationsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("exam")
@Api
public class ExaminationsController {

	@Autowired
	private ExaminationsService examinationsService;
	
	@ApiOperation(value = "Phương thức lấy thông tin chi tiết bộ đề thi dựa vào ID.")
	@GetMapping("/{quizId}")
	public QuizResponseDomain startQuizTest(@PathParam("quizId") String quizId) {
		QuizResponseDomain quizResponse = examinationsService.startExamination(quizId);
		return quizResponse;
	}
}
