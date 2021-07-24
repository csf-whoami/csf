package com.csf.whoami.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.whoami.database.models.TbUserGroup;

public interface UserGroupRepository extends JpaRepository<TbUserGroup, String> {
	TbUserGroup findAllByUserIdAndGroupId(String userId, String groupId);
}
