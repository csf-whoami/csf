package com.csf.database.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csf.base.domain.SearchVO;
import com.csf.base.domain.response.UserInfo;
import com.csf.database.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT new com.csf.base.domain.response.UserInfo(acc.id, acc.username, user.email, acc.createdAt, acc.activedAt, role.id, role.name) "
            + " FROM AccountEntity acc "
            + " INNER JOIN UserEntity user ON user.id = acc.userId "
            + " INNER JOIN TbUserRole userRole ON acc.id = userRole.userId "
            + " INNER JOIN TbRole role ON role.id = userRole.roleId "
            + " WHERE (:#{#search.keyword} IS NULL OR user.email LIKE %:#{#search.keyword}%"
            + "                                    OR acc.username LIKE %:#{#search.keyword}%)"
            + " AND acc.deletedAt IS NULL "
            + " AND user.deletedAt IS NULL "
            + " AND userRole.deletedAt IS NULL "
            + " AND role.deletedAt IS NULL "
            + " ORDER BY acc.createdAt ")
    Page<UserInfo> findAllByCondition(@Param("search") SearchVO search, Pageable pageable);

    @Query(value = "SELECT tbu "
            + " FROM UserEntity tbu "
            + " WHERE tbu.id IN (:ids)"
            + " ORDER BY tbu.createdAt ")
    List<UserEntity> findAllByIds(@Param("ids") List<Long> ids);
}
