package com.csf.whoami.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csf.base.domain.TypeInfo;
import com.csf.whoami.database.models.TbType;

@Repository
public interface TypeRepository extends JpaRepository<TbType, String> {

    @Query(value = "SELECT new com.csf.base.domain.TypeInfo(type.id, type.typeName)"
            + " FROM TbType type"
            + " WHERE type.groupName = :group")
    List<TypeInfo> findAllByGroup(@Param("group") String group);
}
