package com.csf.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csf.base.domain.TypeInfo;
import com.csf.database.models.TypeEntity;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, String> {

    @Query(value = "SELECT new com.csf.base.domain.TypeInfo(type.id, type.name)"
            + " FROM TypeEntity type")
    List<TypeInfo> findAllByGroup();
//    List<TypeInfo> findAllByGroup(@Param("group") String group);
}
