package com.csf.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.database.models.TbQuizTest;

public interface QuizTestRepository extends JpaRepository<TbQuizTest, String> {

}
