package com.csf.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csf.database.models.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByCode(String roleName);

    @Query(value = "SELECT role"
            + " FROM RoleEntity role"
            + " INNER JOIN TbUserRole userRole ON role.id = userRole.roleId"
            + " INNER JOIN AccountEntity user ON user.id = userRole.userId"
            + " ORDER BY role.createdAt")
    List<RoleEntity> findAllByUserId(@Param("userId")Long id);
}
