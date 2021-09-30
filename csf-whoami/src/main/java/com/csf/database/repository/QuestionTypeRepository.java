package com.csf.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csf.database.models.TbQuestionType;

@Repository
public interface QuestionTypeRepository extends JpaRepository<TbQuestionType, Long> {
}
