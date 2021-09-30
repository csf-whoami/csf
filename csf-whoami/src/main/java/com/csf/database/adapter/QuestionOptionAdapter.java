package com.csf.database.adapter;

import java.util.ArrayList;
import java.util.List;

import com.csf.base.domain.AnswerOption;
import com.csf.base.utilities.StringUtils;
import com.csf.database.models.TbQuestionOption;

public class QuestionOptionAdapter {

    /**
     * Convert model to DTO.
     * 
     * @param entity
     * @return
     */
    public static AnswerOption modelToDomain(TbQuestionOption entity) {
        if (entity == null) {
            return null;
        }
        AnswerOption domain = new AnswerOption();
        domain.setId(StringUtils.fromLong(entity.getId()));
        domain.setAnswer(entity.getAnswer());
        domain.setPriority(StringUtils.fromInteger(entity.getPriority()));
        return domain;
    }

    /**
     * Convert DTO to model.
     * 
     * @param domain
     * @return
     */
    public static TbQuestionOption domainToModel(AnswerOption domain) {
        if (domain == null) {
            return null;
        }
        TbQuestionOption entity = new TbQuestionOption();
        entity.setId(StringUtils.toLongOrNull(domain.getId()));
        entity.setAnswer(domain.getAnswer());
        entity.setPriority(StringUtils.toIntegerOrNull(domain.getPriority()));
        return entity;
    }

    /**
     * Convert DTO to list Entity.
     * 
     * @param questionId
     * @param answers
     * @return
     */
    public static List<TbQuestionOption> domainsToModels(Long questionId, List<AnswerOption> answers) {

        List<TbQuestionOption> options = new ArrayList<>();
        for (int i=0; i<answers.size(); i++) {
            TbQuestionOption option = domainToModel(answers.get(i));
            option.setQuestionId(questionId);
            option.setPriority(i + 1);
            options.add(option);
        }

        return options;
    }
}
