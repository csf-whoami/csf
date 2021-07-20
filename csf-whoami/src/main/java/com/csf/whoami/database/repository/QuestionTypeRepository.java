package com.csf.whoami.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csf.whoami.database.model.TbQuestionType;

@Repository
public interface QuestionTypeRepository extends JpaRepository<TbQuestionType, Long> {
}
