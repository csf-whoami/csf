package com.csf.whoami.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.whoami.database.model.TbQuizTest;

public interface QuizTestRepository extends JpaRepository<TbQuizTest, String> {

}
