package com.csf.whoami.database.adapter;

import com.csf.base.domain.QuestionInfo;
import com.csf.base.domain.YesNo;
import com.csf.base.utilities.StringUtils;
import com.csf.whoami.database.models.TbQuestion;

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
        entity.setIsSuperSize(YesNo.N);
        entity.setRandomAnswer(YesNo.N);
        entity.setIsLock(YesNo.N);
        entity.setIsPublish(YesNo.Y);

        entity.setIsPrivate(YesNo.Y);
        entity.setIsMultipleChoice(YesNo.N);

        return entity;
    }

    private static TbQuestion initialEntity() {
        TbQuestion entity = new TbQuestion();
        return entity;
    }

    public static void setDataToEntity(QuestionInfo data, TbQuestion question) {
        question.setContent(data.getContent());
//        question.setOwnerId(AuthenticationUtils.getCurrentAccountId());
        question.setIsSuperSize(YesNo.N);
        question.setRandomAnswer(YesNo.N);
        question.setIsLock(YesNo.N);
        question.setIsPublish(YesNo.Y);

        question.setIsPrivate(YesNo.Y);
        question.setIsMultipleChoice(YesNo.N);
    }
}
