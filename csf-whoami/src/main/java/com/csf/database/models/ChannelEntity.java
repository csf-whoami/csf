package com.csf.database.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.csf.base.domain.enumtype.YesNoEnum;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "W_CHANNELS")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class ChannelEntity extends BaseEntity {

    private static final long serialVersionUID = 5123298365018087795L;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private String url;

    @Column(name = "GROUP_ID")
    private Long groupId;

    @Enumerated(EnumType.STRING)
    @Column(name = "IS_CLOSED")
    private YesNoEnum isClosed;

    @Enumerated(EnumType.STRING)
    @Column(name = "IS_PRIVATE")
    private YesNoEnum isPrivate;

    @Enumerated(EnumType.STRING)
    @Column(name = "IS_LOCK")
    private YesNoEnum isLock;

    @Column(name = "DESCRIPTION")
    private String description;
}
