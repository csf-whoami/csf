package com.csf.database.adapter;

import com.csf.base.domain.QuestionInfo;
import com.csf.base.domain.YesNo;
import com.csf.base.utilities.StringUtils;
import com.csf.database.models.TbQuestion;

public class QuestionAdapter {

    public static QuestionInfo modelToDomain(TbQuestion entity) {
        if (entity == null) {
            return null;
        }
        QuestionInfo domain = new QuestionInfo();
        domain.setId(String.valueOf(entity.getId()));
        return domain;
    }
    
    public static TbQuestion domainToModel(QuestionInfo domain) {
        if (domain == null) {
            return null;
        }
        TbQuestion entity = initialEntity();
        entity.setId(StringUtils.toLongOrNull(domain.getId()));
        entity.setContent(domain.getContent());
//        entity.setOwnerId(AuthenticationUtils.getCurrentAccountId());
        entity.setIsSuperSize(YesNoEnum.N);
        entity.setRandomAnswer(YesNoEnum.N);
        entity.setIsLock(YesNoEnum.N);
        entity.setIsPublish(YesNoEnum.Y);

        entity.setIsPrivate(YesNoEnum.Y);
        entity.setIsMultipleChoice(YesNoEnum.N);

        return entity;
    }

    private static TbQuestion initialEntity() {
        TbQuestion entity = new TbQuestion();
        return entity;
    }

    public static void setDataToEntity(QuestionInfo data, TbQuestion question) {
        question.setContent(data.getContent());
//        question.setOwnerId(AuthenticationUtils.getCurrentAccountId());
        question.setIsSuperSize(YesNoEnum.N);
        question.setRandomAnswer(YesNoEnum.N);
        question.setIsLock(YesNoEnum.N);
        question.setIsPublish(YesNoEnum.Y);

        question.setIsPrivate(YesNoEnum.Y);
        question.setIsMultipleChoice(YesNoEnum.N);
    }
}
