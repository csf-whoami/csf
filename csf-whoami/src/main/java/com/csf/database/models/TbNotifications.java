/**
 * 
 */
package com.csf.database.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "W_TB_NOTIFICATION")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class TbNotifications extends BaseEntity {

    @Column(name = "NOTIFY_TYPE")
    private String notifyType;
    @Column(name = "NOTIFY_LINK")
    private String notifyLink;
    @Column(name = "NOTIFY_STATUS")
    private String notifyStatus;
    @Column(name = "EXPIRE_DATE")
    private Date expireDate;

    @Column(name = "INVITE_FROM")
    private Long inviteFrom;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "INVITE_FROM", insertable = false, updatable = false)
//    @OneToOne(optional = true)
//    @JoinColumn(name = "INVITE_FROM", foreignKey = @ForeignKey(name = "FK_S01DT_USER_H12_2"))
//    private TbAccount invite;

    @Column(name = "INVITE_TO")
    private Long inviteTo;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "INVITE_TO", insertable = false, updatable = false)
////    @OneToOne(optional = true)
////    @JoinColumn(name = "INVITE_TO", foreignKey = @ForeignKey(name = "FK_S01DT_USER_H12"))
//    private TbAccount beInvited;
}
