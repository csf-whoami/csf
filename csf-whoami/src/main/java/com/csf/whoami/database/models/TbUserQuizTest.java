/**
 * 
 */
package com.csf.whoami.database.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "W_TB_USER_QUIZ")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class TbUserQuizTest extends BaseEntity {

    @Column(name = "USER_ID")
    private String userId;
//    @OneToOne(optional = false)
//    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_S01DT_USER_H08"))
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
//    private TbAccount user;

    @Column(name = "QUIZ_ID")
    private String quizTestId;
//    @JoinColumn(name = "QUIZ_ID", foreignKey = @ForeignKey(name = "FK_H07DT_QUIZ_TEST_H09"))
//    private TbQuizTest quizTest;

    @Column(name = "TIME_START")
    private Date timeStart;

    @Column(name = "TIME_END")
    private Date timeEnd;

    @Column(name = "FINISH_STATUS")
    private String isFinish;
}
