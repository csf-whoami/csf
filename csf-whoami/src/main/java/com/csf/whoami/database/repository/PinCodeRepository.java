package com.csf.whoami.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csf.whoami.database.model.TbPinCode;

@Repository
public interface PinCodeRepository extends JpaRepository<TbPinCode, Long> {
}
