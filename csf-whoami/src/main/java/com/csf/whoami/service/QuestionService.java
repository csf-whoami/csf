package com.csf.whoami.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.csf.base.domain.QuestionInfo;
import com.csf.base.domain.QuestionManagementInfo;

public interface QuestionService {

    Page<QuestionManagementInfo> fetchQuestionsByChannel(Long channelId, Pageable pageable);

    QuestionInfo questionInfo(Long group, Long channel);

    Long registerOrUpdateDatas(QuestionInfo data);

    QuestionInfo findById(Long id);

//    QuestionInfo findAll();
}
