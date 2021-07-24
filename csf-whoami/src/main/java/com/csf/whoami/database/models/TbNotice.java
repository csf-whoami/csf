package com.csf.whoami.database.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bb_tb_notice")
@Where(clause = "deleted_at is null")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class TbNotice extends BaseEntity {

    @Column(name = "tb_admin_id")
    private Long tbAdminId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "is_important")
    private String isImportant;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "start_at")
    private Date startAt;

    @Column(name = "finish_at")
    private Date finishAt;
}