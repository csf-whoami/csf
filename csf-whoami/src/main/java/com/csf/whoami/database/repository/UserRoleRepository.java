package com.csf.whoami.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csf.whoami.database.model.TbUserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<TbUserRole, Long> {

    TbUserRole findByUserIdAndRoleId(Long userId, Long roleId);

    TbUserRole findByUserId(Long id);

    @Query(value = "SELECT userRole "
            + " FROM TbUserRole userRole "
            + " WHERE userRole.userId IN (:ids)"
            + " ORDER BY userRole.createdAt ")
    List<TbUserRole> findAllByIds(@Param("ids") List<Long> ids);
}
