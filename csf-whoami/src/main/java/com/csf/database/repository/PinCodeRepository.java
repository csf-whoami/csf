package com.csf.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csf.database.models.TbPinCode;

@Repository
public interface PinCodeRepository extends JpaRepository<TbPinCode, Long> {
}
