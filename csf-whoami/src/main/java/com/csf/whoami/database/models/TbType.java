package com.csf.whoami.database.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "W_TB_TYPE")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class TbType extends BaseEntity {

    @Column(name = "TYPE_NAME")
    private String typeName;

    @Column(name = "GROUP_NAME")
    private String groupName;
}
