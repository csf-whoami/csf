package com.csf.database.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

import com.csf.base.domain.enumtype.AccountTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="W_ACCOUNTS")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity extends BaseEntity {


    private static final long serialVersionUID = -9124663861846934365L;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    @JsonIgnore
    private String password;

    @Column(name = "USER_ID")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private AccountTypeEnum type;

    @Column(name = "ACTIVED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activedAt;

    @Column(name = "STARTED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startedAt;

    @Column(name = "FINISHED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishedAt;
}
