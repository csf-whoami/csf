/**
 * 
 */
package com.csf.whoami.database.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "W_TB_INVITE")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class TbInvites extends BaseEntity {

    @Column(name = "INVITE_TYPE")
    private String inviteType;

    @Column(name = "OWNER")
    private String ownerId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER", insertable = false, updatable = false)
//    @OneToOne(optional = true)
//    @JoinColumn(name = "OWNER", foreignKey = @ForeignKey(name = "FK_S01DT_USER_H13"))
    private TbAccount owner;

    @Column(name = "BE_INVITED")
    private String invitedId;
//    @OneToOne(optional = true)
//    @JoinColumn(name = "BE_INVITED", foreignKey = @ForeignKey(name = "FK_S01DT_USER_H13_2"))
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "BE_INVITED", insertable = false, updatable = false)
//    private TbAccount beInvited;

    @Column(name = "EXPIRE_DATE")
    private Date expireDate;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "DETAIL_ID")
    private String detailId;

    @Column(name = "CONDITION")
    private String condition = null;
}
