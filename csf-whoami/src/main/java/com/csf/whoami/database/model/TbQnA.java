package com.csf.whoami.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.Version;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bb_tb_qna")
@Where(clause = "deleted_at is null")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class TbQnA extends BaseEntity {

    @Column(name = "tb_user_id")
    private Long tbUserId;

    @Column(name = "tb_admin_id")
    private Long tbAdminId;

    @Column(name = "tb_qna_type_id")
    private Long tbQnaTypeId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "reply")
    private String reply;

    @Column(name = "is_replied")
    private String isReplied;

    @Column(name = "file_path")
    private String filePath;

    @Version
    @Column(name = "version")
    private Long version;
}