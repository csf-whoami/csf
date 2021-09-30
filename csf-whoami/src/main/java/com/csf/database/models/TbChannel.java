package com.csf.database.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.csf.base.domain.YesNo;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "W_TB_CHANNEL")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class TbChannel extends BaseEntity {

    @Column(name = "CHANNEL_NAME")
    private String channelName;

    @Column(name = "CHANNEL_URL")
    private String channelUrl;

    @Column(name = "GROUP_ID")
    private Long groupId;

    @Enumerated(EnumType.STRING)
    @Column(name = "IS_CLOSED")
    private YesNo isClosed;

    @Enumerated(EnumType.STRING)
    @Column(name = "IS_PRIVATE")
    private YesNo isPrivate;

    @Enumerated(EnumType.STRING)
    @Column(name = "IS_LOCK")
    private YesNo isLock;

    @Column(name = "CHANNEL_DESCRIPTION")
    private String channelDescription;
}
