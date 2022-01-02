package com.csf.whoami.service;

import java.util.Map;

import com.csf.base.domain.response.QuestionTemplate;

public interface TemplateService {

	QuestionTemplate tempQuestion(String questionType);
	String tempQuestionDetail(Long questionId);
	String tempQuizContent(String questionType);
	String tempQuizPreview(String questionType, Long questionId);
	String getContent(String string, Map<String, Object> params);
//	String exportHtmlBody(String htmlContent);
	boolean sendMail(String email, String string, String string2, Map<String, Object> params);
}
