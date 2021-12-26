package com.csf.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csf.database.models.PinCodeEntity;

@Repository
public interface PinCodeRepository extends JpaRepository<PinCodeEntity, Long> {
}
