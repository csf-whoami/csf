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

import com.csf.base.domain.YesNo;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "W_TB_PIN_CODE")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class PinCodeEntity extends BaseEntity {

    private static final long serialVersionUID = -5366594102937921580L;

    @Column(name = "GROUP_TYPE")
    private String groupType;

    @Column(name = "CONTENT_ID")
    private Long contentId;

    @Column(name = "PIN_CODE")
    private String pinCode;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "IS_PRIVATE")
    private YesNo isPrivate;

    @Column(name = "ACTIVED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activedAt;
}
