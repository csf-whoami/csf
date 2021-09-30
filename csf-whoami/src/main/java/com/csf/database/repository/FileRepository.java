package com.csf.database.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csf.database.models.UploadFile;

public interface FileRepository extends JpaRepository<UploadFile, Integer> {

    public UploadFile findOneByFileName(String fileName);
    @Query("SELECT m FROM UploadFile m WHERE m.id = :title")
    UploadFile findOne(@Param("title") int title);
}
