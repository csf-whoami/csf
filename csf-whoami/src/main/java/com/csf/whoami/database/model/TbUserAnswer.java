/**
 * 
 */
package com.csf.whoami.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "W_TB_USER_ANSWER")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class TbUserAnswer extends BaseEntity {

    @Column(name = "USER_ID")
    private String userId;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
//    @OneToOne(optional = false)
//    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_S01DT_USER_H08"))
//    private TbAccount user;

    @Column(name = "QUESTION_ID")
    private String questionId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "QUESTION_ID", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "QUESTION_ID", foreignKey = @ForeignKey(name = "FK_H06DT_QUESTION_H11"))
//    private TbQuestion question;

    @Column(name = "USER_ANSWER")
    private String userAnswer;

    @Column(name = "TIME_FINISH")
    private int timeToFinish;
}
