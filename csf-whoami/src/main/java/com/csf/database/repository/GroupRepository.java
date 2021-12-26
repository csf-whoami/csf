package com.csf.database.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csf.base.dao.CommonRepository;
import com.csf.base.domain.SearchVO;
import com.csf.base.domain.response.GroupInfo;
import com.csf.database.models.GroupEntity;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long>, CommonRepository {

    GroupEntity findByGroupUrl(String groupUrl);

    @Query(value = "select group.* from H04DT_GROUPS group "
            + "inner join H05DT_USER_GROUP userGroup on group.GROUP_ID = userGroup.GROUP_ID "
            + "inner join H01DT_USERS users on users.USER_ID = userGroup.USER_ID "
            + "where users.user_id =:userId and group.GROUP_TYPE = :groupType and group.GROUP_NAME = :groupName ", nativeQuery = true)
    GroupEntity findMyGroup(@Param("groupName") String groupName, @Param("groupType") String groupType,
            @Param("userId") String userId);

    @Query(value = "select groups.* from h04dt_groups groups \n"
            + "inner join h05dt_user_group userGroup on groups.GROUP_ID = userGroup.GROUP_ID \n"
            + "inner join h01dt_users users on users.USER_ID = userGroup.USER_ID "
            + "where users.user_id =:userId", nativeQuery = true)
    List<GroupEntity> findAllByUser(@Param("userId") String userId);

    @Query(value = "select groups.* from h04dt_groups groups \n"
            + "inner join h05dt_user_group userGroup on groups.GROUP_ID = userGroup.GROUP_ID \n"
            + "inner join h01dt_users users on users.USER_ID = userGroup.USER_ID "
            + "where users.userName =:username", nativeQuery = true)
    List<GroupEntity> findAllByUsername(@Param("username") String username);

//    List<TbGroup> findAllByParentGroup(String groupId);
    GroupEntity findByGroupUrlAndGroupType(String groupName, String groupType);

//    TbGroup findByIdAndParentGroup(String groupId, String parentGroup);

    @Query(value = "SELECT new com.csf.base.domain.response.GroupInfo(tbgroup.id, tbgroup.groupName, tbgroup.groupType, "
    		+ "                                                               tbgroup.isPrivate, tbgroup.isPublish, tbgroup.isLock, tbgroup.createdAt)" +
            " FROM TbGroup tbgroup" +
            " WHERE (:#{#search.keyword} IS NULL OR tbgroup.groupName LIKE %:#{#search.keyword}%" +
            "                                     OR tbgroup.groupUrl LIKE %:#{#search.keyword}%" +
            "                                     OR tbgroup.groupType LIKE %:#{#search.keyword}%)")
    Page<GroupInfo> groupList(@Param("search") SearchVO search, Pageable pageable);

//    @Query(value = "SELECT new com.csf.whoami.common.domain.GroupDomain(m.id, m.groupName, m.groupUrl, m.groupType)"
//            + " FROM TbGroup m "
//            + " WHERE m.id=:id"
//    )
//    GroupDomain groupDetail(@Param("id") Long id);
}
