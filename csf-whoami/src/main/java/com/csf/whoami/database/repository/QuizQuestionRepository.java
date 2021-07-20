package com.csf.whoami.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.whoami.database.model.TbQuizQuestion;

/**
 * @author tuan
 *
 */
public interface QuizQuestionRepository extends JpaRepository<TbQuizQuestion, String> {

	List<TbQuizQuestion> findAllByQuizId(Long id);
}
