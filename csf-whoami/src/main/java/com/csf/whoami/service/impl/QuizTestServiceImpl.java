/**
 * 
 */
package com.csf.whoami.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.csf.base.exception.CustomException;
import com.csf.base.exception.ErrorException;
import com.csf.base.exception.HttpStatus;
import com.csf.base.utilities.StringUtils;
import com.csf.whoami.database.dto.quiz.AnswerRequestDomain;
import com.csf.whoami.database.dto.quiz.QuizAnswersDomain;
import com.csf.whoami.database.dto.quiz.QuizRequestDomain;
import com.csf.whoami.database.dto.quiz.ResultResponseDomain;
import com.csf.whoami.database.models.TbQuestion;
import com.csf.whoami.database.models.TbQuizQuestion;
import com.csf.whoami.database.models.TbQuizTest;
import com.csf.whoami.database.repository.QuestionRepository;
import com.csf.whoami.database.repository.QuizQuestionRepository;
import com.csf.whoami.database.repository.QuizTestRepository;
import com.csf.whoami.service.QuizTestService;

@Service
public class QuizTestServiceImpl implements QuizTestService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizTestRepository quizTestRepository;
    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    /**
     * Generate random quiz test with number and time particular.
     */
    @Transactional
    @Override
    public String generateRandomQuiz(QuizRequestDomain domain) {

        // Validate data.
//        Integer questionNumber = StringUtils.convertStringToIntegerOrNull(domain.getQuestionNumber());
//        if (!StringUtils.validateStringFormat(domain.getQuestionTime(), StringUtils.HOUR_MINUTE_PATTERN) 
//                || questionNumber == null) {
//            throw new CustomException(ErrorException.INVALID_FORMAT.getMessage(),
//                    ErrorException.INVALID_FORMAT.getCode(), HttpStatus.BAD_REQUEST);
//        }

        Integer questionNumber = Integer.parseInt(domain.getQuestionNumber());
        List<TbQuestion> questions = questionRepository.getQuestionsByGroupId(StringUtils.toLongOrNull(domain.getGroupId()));
        if (questions == null) {
            throw new CustomException(ErrorException.GROUP_NO_QUESTIONS.getMessage(),
            		ErrorException.GROUP_NO_QUESTIONS.getCode(),
            		HttpStatus.BAD_REQUEST);
        }

        return createQuizTest(Integer.parseInt(domain.getQuestionTime()), questions);
    }

    /**
     * Calculate quiz test result.
     * @author tuan
     */
    @Transactional
    @Override
    public ResultResponseDomain calculateExaminationResult(QuizAnswersDomain domain) {

        // Check QuizTest.
        TbQuizTest quiz = quizTestRepository.findById(domain.getQuizId()).orElse(null);
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

        List<AnswerRequestDomain> answers = domain.getAnswers();
        boolean markNowStatus = validateAndCheckCalculateQuestionsNow(quizQuests, answers);

        // TODO: Compare and calculate.
        if (markNowStatus) {
//            int result = calculateQuizMark(quizQuests, domain.getAnswers());
            ResultResponseDomain resultResponse = new ResultResponseDomain();
//            resultResponse.setTotalMark(StringUtils.convertObjectToString(result));
//            resultResponse.setMarkStatus(RequestResponseConstants.MarkStatusConstant.MARK_NOW.getValue());
            return resultResponse;
        }
        // Mark late
        return null;
    }

    /**
     * Create quiz test random question.
     * 
     * @author tuan
     * @param timeQuiz
     * @param questions
     * @return
     */
    @Transactional
    private String createQuizTest(int timeQuiz, List<TbQuestion> questions) {

        // Create Quiz.
        TbQuizTest quizTest = new TbQuizTest();
        quizTest.setQuestionTime(timeQuiz);    // Minute
        quizTest = quizTestRepository.save(quizTest);
        if(quizTest == null) {
            throw new CustomException(ErrorException.INVALID_FORMAT.getMessage(),
            		ErrorException.INVALID_FORMAT.getCode(),
            		HttpStatus.BAD_REQUEST);
        }

        // Add question to quiz.
        List<TbQuizQuestion> quizQuestions = new ArrayList<>();
        TbQuizQuestion entity = null;
        for(TbQuestion item : questions) {
            entity = new TbQuizQuestion();
//            entity.setQuestion(item);
//            entity.setQuiz(quizTest);
            quizQuestions.add(entity);
        }
        quizQuestions = quizQuestionRepository.saveAll(quizQuestions);
        if (CollectionUtils.isEmpty(quizQuestions)) {
            throw new CustomException(ErrorException.CANT_SAVE_QUIZ.getMessage(),
            		ErrorException.CANT_SAVE_QUIZ.getCode(),
            		HttpStatus.BAD_REQUEST);
        }

        return String.valueOf(quizTest.getId());
    }

    /**
     * Calculate quiz test mark.
     * 
     * @author mba0051
     * @param quizQuests
     * @param answers
     * @return
     */
    @SuppressWarnings("unused")
    private int calculateQuizMark(List<TbQuizQuestion> quizQuests, List<AnswerRequestDomain> answers) {

        List<String> correctResult = new ArrayList<>();
//        answers.forEach(ans -> quizQuests.stream()
//                .filter(
//                quiz -> String.valueOf(quiz.getQuestion().getId()).equalsIgnoreCase(ans.getQuestionId()))
//                .findAny()
//                .ifPresent(match -> {
//                    if (CommonConstants.SelectOption.equalsIgnoreCase(match.getQuestion().getQuestionType())
//                            || CommonConstants.PictureChoiceType.equalsIgnoreCase(
//                                    match.getQuestion().getQuestionType())) {
//                        // TODO: XML
////                        correctResult.add(match.getId());
//
//                    } else if (CommonConstants.TextType.equalsIgnoreCase(
//                            match.getQuestion().getQuestionType())) {
//                        if (DatabaseConstants.YesNoConstant.NO.getValue().equals(match.getQuestion().getIsMultipleChoice())
//                                && match.getQuestion().getCorrectAnswer().trim().equals(ans.getAnswerContent())) {
//                            correctResult.add(String.valueOf(match.getId()));
//                        }
//                    } else if (CommonConstants.YesNoType.equalsIgnoreCase(
//                            match.getQuestion().getQuestionType()) 
//                            && (match.getQuestion().getCorrectAnswer().trim().equalsIgnoreCase(ans.getAnswerContent()))) {
//                        correctResult.add(String.valueOf(match.getId()));
//                    }
//                })
//            );

        return correctResult.size();
    }

    /**
     * Compare equal question number and mapping ID.
     * @author tuan
     * @param quizQuests
     * @param answers
     */
    private boolean validateAndCheckCalculateQuestionsNow(
            List<TbQuizQuestion> quizQuests, List<AnswerRequestDomain> answers) {
        if (quizQuests.size() != answers.size()) {
            throw new CustomException(ErrorException.ANSWER_NOT_MAP.getMessage(),
            		ErrorException.ANSWER_NOT_MAP.getCode(),
            		HttpStatus.BAD_REQUEST);
        }

//        int mapItem = answers.stream()
//                .filter(ans -> quizQuests.stream()
//                        .anyMatch(quiz -> String.valueOf(quiz.getQuestion().getId()).equalsIgnoreCase(ans.getQuestionId())))
//                .collect(Collectors.toList()).size();
//
//        if (mapItem != quizQuests.size()) {
//            throw new CustomException(ErrorCode.ANSWER_NOT_MAP.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//
//        List<String> markLate = new ArrayList<String>();
//        quizQuests.forEach(quiz -> {
//            if (DatabaseConstants.QuestionTypeConstant.UPLOAD.getValue().equalsIgnoreCase(quiz.getQuestion().getQuestionType())
//                    || DatabaseConstants.QuestionTypeConstant.MULTILINE_TEXT.getValue().equalsIgnoreCase(
//                            quiz.getQuestion().getQuestionType())) {
//                markLate.add(quiz.getQuestionId());
//            }
//        });
//        // TODO: Preparing to get detail AI.
//
//        return CollectionUtils.isEmpty(markLate);
        return true;
    }
}
