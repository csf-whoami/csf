package com.csf.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.database.models.TbUserQuizTest;

public interface UserQuizTestRepository extends JpaRepository<TbUserQuizTest, String> {
}
