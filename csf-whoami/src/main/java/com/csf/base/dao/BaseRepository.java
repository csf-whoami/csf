package com.csf.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csf.database.models.BaseEntity;

@Repository
public interface BaseRepository extends JpaRepository<BaseEntity, Long>, CommonRepository {
}
