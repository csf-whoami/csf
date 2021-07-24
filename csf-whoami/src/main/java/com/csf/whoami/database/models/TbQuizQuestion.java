/**
 * 
 */
package com.csf.whoami.database.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "W_TB_QUIZ_QUESTION")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class TbQuizQuestion extends BaseEntity {

    @Column(name = "QUIZ_ID")
    private String quizId;
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "QUIZ_ID", foreignKey = @ForeignKey(name = "FK_H07DT_QUIZ_TEST_H11"))
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "QUIZ_ID", insertable = false, updatable = false)
//    private TbQuizTest quiz;

    @Column(name = "QUESTION_ID")
    private String questionId;
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "QUESTION_ID", foreignKey = @ForeignKey(name = "FK_H06DT_QUESTION_H11"))
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "QUESTION_ID", insertable = false, updatable = false)
//    private TbQuestion question;
}
