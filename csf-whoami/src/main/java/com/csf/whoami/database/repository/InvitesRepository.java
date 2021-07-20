package com.csf.whoami.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.whoami.database.model.TbInvites;

public interface InvitesRepository extends JpaRepository<TbInvites, String> {
}
