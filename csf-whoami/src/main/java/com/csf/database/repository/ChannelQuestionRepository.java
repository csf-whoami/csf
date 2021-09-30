package com.csf.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csf.database.models.TbChannelQuestion;

@Repository
public interface ChannelQuestionRepository extends JpaRepository<TbChannelQuestion, Long> {
}
