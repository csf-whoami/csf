package com.csf.whoami.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csf.whoami.database.models.TbQuestionOption;

@Repository
public interface QuestionOptionRepository extends JpaRepository<TbQuestionOption, String> {

    List<TbQuestionOption> findAllByQuestionIdOrderByPriority(Long questionId);
}
