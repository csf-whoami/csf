package com.csf.whoami.database.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="W_TB_ACCOUNT")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TbAccount extends BaseEntity {

    @Column(name = "LOGIN_ID")
    private String username;

    @Column(name = "USER_PASSWORD")
    @JsonIgnore
    private String password;

    @Column(name = "USER_ID")
    private Long userId;

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
