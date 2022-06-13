package com.csf.database.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.csf.base.constant.ConstantsDateFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder(toBuilder = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 5239770149129805050L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CREATED_AT")
//    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstantsDateFormat.DATE_TIME_FORMAT, timezone = ConstantsDateFormat.TIMEZONE)
    @CreationTimestamp
    protected Date createdAt;

    @Column(name = "UPDATED_AT")
//    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstantsDateFormat.DATE_TIME_FORMAT, timezone = ConstantsDateFormat.TIMEZONE)
    @CreationTimestamp
    protected Date updatedAt;

    @Column(name = "UPDATED_BY")
    protected Long updatedBy;

    @Column(name = "DELETED_AT")
//    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstantsDateFormat.DATE_TIME_FORMAT, timezone = ConstantsDateFormat.TIMEZONE)
    @CreationTimestamp
    protected Date deletedAt;

    /**
     * Preparing before insert.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
//        updatedBy = AuthenticationUtils.getUser().getId();
        deletedAt = null;
    }

    /**
     * Prepare data before update
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
//        updatedBy = AuthenticationUtils.getUser().getId();
    }

    @PreRemove
    protected void onRemove() {
//        updatedBy = AuthenticationUtils.getUser().getId();
        deletedAt = new Date();
    }
}
