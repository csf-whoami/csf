package com.csf.whoami.database.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "W_TB_GROUP")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class TbGroup extends BaseEntity {

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "GROUP_TYPE")
    private String groupType;

    @Column(name = "IS_PUBLISH")
    private String isPublish;

    @Column(name = "IS_CLOSED")
    private String isClosed;

    @Column(name = "IS_PRIVATE")
    private String isPrivate;

    @Column(name = "IS_LOCK")
    private String isLock;

    @Column(name = "GROUP_DESCRIPTION")
    private String groupDescription;

    @Column(name = "GROUP_URL")
    private String groupUrl;

    @Column(name = "ACTIVED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activedAt;
}
