package com.csf.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csf.account.entity.MsgUser;

@Repository
public interface MsgUserRepository extends JpaRepository<MsgUser, Long> {

}
