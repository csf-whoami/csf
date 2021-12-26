package com.csf.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csf.database.models.TbRole;

@Repository
public interface RoleRepository extends JpaRepository<TbRole, Long> {
    TbRole findByCode(String roleName);

    @Query(value = "SELECT role"
            + " FROM TbRole role"
            + " INNER JOIN TbUserRole userRole ON role.id = userRole.roleId"
            + " INNER JOIN AccountEntity user ON user.id = userRole.userId"
            + " ORDER BY role.createdAt")
    List<TbRole> findAllByUserId(@Param("userId")Long id);
}
