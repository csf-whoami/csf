package com.csf.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.database.models.TbInvites;

public interface InvitesRepository extends JpaRepository<TbInvites, String> {
}
