/**
 * 
 */
package com.csf.whoami.database.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.csf.whoami.database.dto.YesNo;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "W_TB_QUESTION")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class TbQuestion extends BaseEntity {

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "OWNER")
    private Long ownerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "SUPER_SIZE")
    private YesNo isSuperSize = YesNo.N;

    @Enumerated(EnumType.STRING)
    @Column(name = "RANDOM_ANSWER")
    private YesNo randomAnswer = YesNo.N;

    @Enumerated(EnumType.STRING)
    @Column(name = "LOCK_STATUS")
    private YesNo isLock = YesNo.N;

    @Enumerated(EnumType.STRING)
    @Column(name = "PUBLISH_STATUS")
    private YesNo isPublish = YesNo.Y;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIVATE_STATUS")
    private YesNo isPrivate = YesNo.Y;

    @Enumerated(EnumType.STRING)
    @Column(name = "MULTIPLE_CHOICE")
    private YesNo isMultipleChoice = YesNo.N;

    @Column(name = "GROUP_ID")
    private Long groupId;
}
