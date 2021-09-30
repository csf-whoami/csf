package com.csf.database.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "W_TB_QUESTION_OPTION")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class TbQuestionOption extends BaseEntity {

    @Column(name = "QUESTION_ID")
    private Long questionId;
    @Column(name = "ANSWER")
    private String answer;
    @Column(name = "PRIORITY")
    private Integer priority;
}
