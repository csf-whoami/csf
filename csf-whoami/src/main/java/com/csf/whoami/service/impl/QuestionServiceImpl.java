package com.csf.whoami.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.csf.base.domain.AnswerOption;
import com.csf.base.domain.QuestionInfo;
import com.csf.base.domain.QuestionManagementInfo;
import com.csf.base.exception.CustomException;
import com.csf.base.exception.ErrorException;
import com.csf.base.exception.HttpStatus;
import com.csf.base.utilities.StringUtils;
import com.csf.database.adapter.QuestionAdapter;
import com.csf.database.adapter.QuestionOptionAdapter;
import com.csf.database.models.TbChannel;
import com.csf.database.models.TbChannelQuestion;
import com.csf.database.models.TbGroup;
import com.csf.database.models.TbQuestion;
import com.csf.database.models.TbQuestionOption;
import com.csf.database.models.TbQuestionType;
import com.csf.database.repository.ChannelQuestionRepository;
import com.csf.database.repository.ChannelRepository;
import com.csf.database.repository.GroupRepository;
import com.csf.database.repository.QuestionOptionRepository;
import com.csf.database.repository.QuestionRepository;
import com.csf.database.repository.QuestionTypeRepository;
import com.csf.whoami.service.QuestionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final GroupRepository groupRepository;
    private final ChannelRepository channelRepository;
    private final QuestionOptionRepository questionOptionRepository;
    private final ChannelQuestionRepository channelQuestionRepository;
    private final QuestionTypeRepository questionTypeRepository;

    @Override
    public Page<QuestionManagementInfo> fetchQuestionsByChannel(Long channelId, Pageable pageable) {
        if (channelId == null) {
            Page.empty();
        }
        return questionRepository.findAllByChannelId(channelId, pageable);
    }

    @Override
    public QuestionInfo questionInfo(Long group, Long channel) {
        QuestionInfo info = new QuestionInfo();
        TbGroup groupInfo = groupRepository.findById(group).orElse(null);
        TbChannel channelInfo = channelRepository.findById(channel).orElse(null);

        if (groupInfo == null || channelInfo == null) {
            throw new CustomException(ErrorException.DATA_NOT_FOUND.getMessage(),
            		ErrorException.DATA_NOT_FOUND.getCode(),
            		HttpStatus.BAD_REQUEST);
        }

        info.setGroupId(StringUtils.fromLong(groupInfo.getId()));
        info.setGroupName(groupInfo.getGroupName());
        info.setChannelId(StringUtils.fromLong(channelInfo.getId()));
        info.setChannelName(channelInfo.getChannelName());
        return info;
    }

    @Transactional
    @Override
    public Long registerOrUpdateDatas(QuestionInfo data) {
        Long id = StringUtils.toLongOrNull(data.getId());
        if (id == null) {
            return registerQuestion(data);
        }
        return updateQuestion(id, data);
    }

    @Transactional
    private Long updateQuestion(Long id, QuestionInfo data) {
        TbQuestion question = questionRepository.findById(id).orElse(null);
        if (question == null) {
            throw new CustomException(ErrorException.DATA_NOT_FOUND.getMessage(),
            		ErrorException.DATA_NOT_FOUND.getCode(),
            		HttpStatus.BAD_REQUEST);
        }
        QuestionAdapter.setDataToEntity(data, question);
        question = questionRepository.save(question);

        List<TbQuestionOption> dbOptions = questionOptionRepository.findAllByQuestionIdOrderByPriority(id);

        // Update options.
        List<TbQuestionOption> updateOptions = QuestionOptionAdapter.domainsToModels(question.getId(), data.getOptions());

        List<TbQuestionOption> commonOptions = dbOptions.stream()
                .filter(option -> updateOptions.stream()
                        .anyMatch(entity -> option.getId().equals(entity.getId())))
                .collect(Collectors.toList());

        // Insert new Options.
        List<TbQuestionOption> inserteds = insertOptions(updateOptions, commonOptions);
        questionOptionRepository.saveAll(inserteds);

        List<TbQuestionOption> deletes = deleteOptions(dbOptions, commonOptions);
        // Delete options
        questionOptionRepository.saveAll(deletes);

        List<TbQuestionOption> updates = updateOptions(commonOptions, question.getId());
        // Update options.
        questionOptionRepository.saveAll(updates);

        return question.getId();
    }

    @Transactional
    private Long registerQuestion(QuestionInfo data) {
        // Save question.
        TbQuestion question = QuestionAdapter.domainToModel(data);
        question = questionRepository.save(question);

        TbChannelQuestion channelQuestion = new TbChannelQuestion();
        channelQuestion.setChannelId(StringUtils.toLongOrNull(data.getChannelId()));
        channelQuestion.setQuestionId(question.getId());
        channelQuestionRepository.save(channelQuestion);

        TbQuestionType questionType = new TbQuestionType();
        questionType.setQuestionId(question.getId());
        questionType.setTypeId(StringUtils.toLongOrNull(data.getQuestionType()));
        questionTypeRepository.save(questionType);

        // Save options.
        List<TbQuestionOption> options = QuestionOptionAdapter.domainsToModels(question.getId(), data.getOptions());
        options = questionOptionRepository.saveAll(options);

        if (CollectionUtils.isEmpty(options)) {
            throw new CustomException(ErrorException.CANT_INSERT_OPTIONS.getMessage(),
            		ErrorException.CANT_INSERT_OPTIONS.getCode(),
            		HttpStatus.BAD_REQUEST);
        }
        return question.getId();
    }

    /**
     * Insert new Options.
     *
     * @param updateOptions
     * @param commonOptions
     */
    private List<TbQuestionOption> insertOptions(List<TbQuestionOption> updateOptions, List<TbQuestionOption> commonOptions) {
        updateOptions.removeAll(updateOptions.stream()
                .filter(option -> commonOptions.stream()
                        .anyMatch(update -> update.getId().equals(option.getId())))
                .collect(Collectors.toList()));
        return updateOptions;
    }

    /**
     * Remove options.
     *
     * @param dbOptions
     * @param commonOptions
     */
    private List<TbQuestionOption> deleteOptions(List<TbQuestionOption> dbOptions, List<TbQuestionOption> commonOptions) {

        dbOptions.removeAll(dbOptions.stream()
                .filter(option -> commonOptions.stream()
                        .anyMatch(update -> update.getId().equals(option.getId())))
                .collect(Collectors.toList()));
        dbOptions.stream().forEach(option -> option.setDeletedAt(new Date()));
        return dbOptions;
    }

    /**
     * Update new questions.
     * 
     * @param commonOptions
     * @param questionId
     * @return
     */
    private List<TbQuestionOption> updateOptions(List<TbQuestionOption> commonOptions, Long questionId) {

        List<TbQuestionOption> dbOptions = questionOptionRepository.findAllByQuestionIdOrderByPriority(questionId);
        for (TbQuestionOption option : dbOptions) {
            for (TbQuestionOption common : commonOptions) {
                if (option.getId().equals(common.getId())) {
                    option.setAnswer(common.getAnswer());
                }
            }
        }
        return dbOptions;
    }

    @Override
    public QuestionInfo findById(Long id) {
        QuestionInfo info = questionRepository.findQuestionById(id);
        if (info == null) {
            throw new CustomException(ErrorException.DATA_INVALID.getMessage(), ErrorException.DATA_INVALID.getCode(), HttpStatus.BAD_REQUEST);
        }
        List<AnswerOption> options = questionOptionRepository.findAllByQuestionIdOrderByPriority(id).stream()
                                                                                     .map(item -> QuestionOptionAdapter.modelToDomain(item))
                                                                                     .collect(Collectors.toList());
        info.setOptions(options);
        return info;
    }
}
