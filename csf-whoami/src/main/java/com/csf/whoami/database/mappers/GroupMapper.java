package com.csf.whoami.database.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.mapstruct.Mapper;

import com.csf.whoami.database.providers.GroupProvider;
import com.csf.whoami.database.view.GroupView;

@Mapper
public interface GroupMapper {

	@SelectProvider(type = GroupProvider.class, method = "findGroupsByUser")
	@Results({
			@Result(id = true, property = "id", column = "ID"),
			@Result(property = "groupName", column = "GROUP_NAME"),
			@Result(property = "groupType", column = "GROUP_TYPE"),
			@Result(property = "isPublish", column = "IS_PUBLISH"),
			@Result(property = "isClosed", column = "IS_CLOSED"),
			@Result(property = "isPrivate", column = "IS_PRIVATE"),
			@Result(property = "isLock", column = "IS_LOCK"),
			@Result(property = "groupDescription", column = "GROUP_DESCRIPTION"),
			@Result(property = "groupUrl", column = "GROUP_URL"),
			@Result(property = "activedAt", column = "ACTIVED_AT")
		})
	List<GroupView> findAllByUser(Long userId);

	@SelectProvider(type = GroupProvider.class, method = "findGroupsByUrl")
	@Results({
			@Result(id = true, property = "id", column = "ID"),
			@Result(property = "groupName", column = "GROUP_NAME"),
			@Result(property = "groupType", column = "GROUP_TYPE"),
			@Result(property = "isPublish", column = "IS_PUBLISH"),
			@Result(property = "isClosed", column = "IS_CLOSED"),
			@Result(property = "isPrivate", column = "IS_PRIVATE"),
			@Result(property = "isLock", column = "IS_LOCK"),
			@Result(property = "groupDescription", column = "GROUP_DESCRIPTION"),
			@Result(property = "groupUrl", column = "GROUP_URL"),
			@Result(property = "activedAt", column = "ACTIVED_AT")
		})
	GroupView findByGroupUrl(String groupUrl);

	@SelectProvider(type = GroupProvider.class, method = "findGroupsByUsername")
	@Results({
			@Result(id = true, property = "id", column = "ID"),
			@Result(property = "groupName", column = "GROUP_NAME"),
			@Result(property = "groupType", column = "GROUP_TYPE"),
			@Result(property = "isPublish", column = "IS_PUBLISH"),
			@Result(property = "isClosed", column = "IS_CLOSED"),
			@Result(property = "isPrivate", column = "IS_PRIVATE"),
			@Result(property = "isLock", column = "IS_LOCK"),
			@Result(property = "groupDescription", column = "GROUP_DESCRIPTION"),
			@Result(property = "groupUrl", column = "GROUP_URL"),
			@Result(property = "activedAt", column = "ACTIVED_AT")
		})
	List<GroupView> findAllByUsername(String username);
}
