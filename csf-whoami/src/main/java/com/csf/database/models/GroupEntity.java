package com.csf.database.models;

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
@Table(name = "W_GROUPS")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class GroupEntity extends BaseEntity {

    private static final long serialVersionUID = -5184620167960702589L;

    @Column(name = "DISPLAY_NAME_VN")
    private String displayNameVn;

    @Column(name = "DISPLAY_NAME_EN")
    private String displayNameEn;

    @Column(name = "GROUP_TYPE")
    private String type;

    @Column(name = "GROUP_OWNER")
    private Long ownerId;

    @Column(name = "IS_PUBLISH")
    private String isPublish;

    @Column(name = "IS_CLOSED")
    private String isClosed;

    @Column(name = "IS_PRIVATE")
    private String isPrivate;

    @Column(name = "IS_LOCK")
    private String isLock;

    @Column(name = "DESCRIPTION")
    private String groupDescription;

    @Column(name = "URL")
    private String url;

    @Column(name = "ACTIVED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activedAt;
}
