package com.csf.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csf.database.models.ChannelQuestionEntity;

@Repository
public interface ChannelQuestionRepository extends JpaRepository<ChannelQuestionEntity, Long> {
}
