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
@Table(name = "W_TYPES")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class TypeEntity extends BaseEntity {

	private static final long serialVersionUID = 9221200334778851759L;

	@Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ACTIVED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activedAt;

    @Column(name = "NOTE")
    private String note;
}
