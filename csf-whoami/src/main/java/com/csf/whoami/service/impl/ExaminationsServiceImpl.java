/**
 * 
 */
package com.csf.whoami.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.csf.base.constant.ConstantsDateFormat;
import com.csf.base.domain.quiz.QuizResponseDomain;
import com.csf.base.exception.CustomException;
import com.csf.base.exception.ErrorException;
import com.csf.base.exception.HttpStatus;
import com.csf.base.utilities.DateTimeUtils;
import com.csf.whoami.database.models.TbQuizQuestion;
import com.csf.whoami.database.models.TbQuizTest;
import com.csf.whoami.database.models.TbUserQuizTest;
import com.csf.whoami.database.repository.QuizQuestionRepository;
import com.csf.whoami.database.repository.QuizTestRepository;
import com.csf.whoami.database.repository.UserQuizTestRepository;
import com.csf.whoami.service.ExaminationsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExaminationsServiceImpl implements ExaminationsService {

	private final QuizTestRepository quizTestRepository;
	private final UserQuizTestRepository userQuizTestRepository;
	private final QuizQuestionRepository quizQuestionRepository;

	/**
	 * Start quiz test.
	 * @author mba0051
	 */
	@Override
	@Transactional
	public QuizResponseDomain startExamination(String quizId) {

		TbQuizTest quizTest = quizTestRepository.findById(quizId).orElse(null);
		if (quizTest == null) {
			throw new CustomException(ErrorException.INVALID_QUIZ.getMessage(),
					ErrorException.INVALID_QUIZ.getCode(),
					HttpStatus.BAD_REQUEST);
		}

//		String userId = AuthenticationUtils.getCurrentUserId();
		TbUserQuizTest userQuiz = new TbUserQuizTest();
//		userQuiz.setUserId(userId);
//		userQuiz.setQuizTest(quizTest);
		userQuiz = userQuizTestRepository.save(userQuiz);
		if (userQuiz == null) {
			throw new CustomException(ErrorException.CANT_MAKE_EXAM.getMessage(),
					ErrorException.CANT_MAKE_EXAM.getCode(),
					HttpStatus.BAD_REQUEST);
		}

		return startQuiztest(quizId);
	}

	
	
	/**
	 * Start quiz test.
	 * 
	 * @author mba0051
	 * @param quizId
	 * @return
	 */
	@Transactional
	private QuizResponseDomain startQuiztest(String quizId) {

		TbQuizTest quiz = quizTestRepository.findById(quizId).orElse(null);
		if (quiz == null) {
			throw new CustomException(ErrorException.NOT_EXIST_QUIZ.getMessage(),
					ErrorException.NOT_EXIST_QUIZ.getCode(),
					HttpStatus.BAD_REQUEST);
		}
		// Get quiz questions.
		List<TbQuizQuestion> quizQuests = quizQuestionRepository.findAllByQuizId(quiz.getId());
		if (CollectionUtils.isEmpty(quizQuests)) {
			throw new CustomException(ErrorException.INVALID_QUIZ.getMessage(),
					ErrorException.INVALID_QUIZ.getCode(),
					HttpStatus.BAD_REQUEST);
		}
		return convertToQuizDomain(quizId, quizQuests, quiz.getGroupId(), quiz.getQuestionTime());
	}

	/**
	 * Convert data to response domain.
	 * 
	 * @author mba0051
	 * @param quizId
	 * @param quizQuests
	 * @param groupId
	 * @param questionTime
	 * @return
	 */
	private QuizResponseDomain convertToQuizDomain(String quizId, List<TbQuizQuestion> quizQuests, 
			String groupId, int questionTime) {
		QuizResponseDomain responseDomain = new QuizResponseDomain();
		responseDomain.setQuizId(quizId);
//		responseDomain.setDuration(StringUtils.convertObjectToString(questionTime));
		try {
			responseDomain.setTimeStart(DateTimeUtils.getSysDateTime(ConstantsDateFormat.YYYYMMDDhhmm));
		} catch (Exception e) {
			throw new CustomException(ErrorException.INVALID_FORMAT.getMessage(),
					ErrorException.INVALID_FORMAT.getCode(),
					HttpStatus.BAD_REQUEST);
		}

		// TODO
//		List<QuestionDomain> domain = quizQuests.stream().map(item -> new QuestionDomain(item.getId(), 
//				item.getQuestion().getQuestionType(), 
//				item.getQuestion().getContent(),
//				groupId)).collect(Collectors.toList());
//		responseDomain.setQuestions(domain);
		return responseDomain;
	}
}
