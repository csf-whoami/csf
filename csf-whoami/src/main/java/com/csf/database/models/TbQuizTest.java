/**
 * 
 */
package com.csf.database.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "W_TB_QUIZ_TEST")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class TbQuizTest extends BaseEntity {

    @Column(name = "OWNER")
    private String ownerId;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "OWNER", insertable = false, updatable = false)
//    @OneToOne(optional = false)
//    @JoinColumn(name = "OWNER", foreignKey = @ForeignKey(name = "FK_S01DT_USER_H07"))
//    private TbAccount owner;

    @Column(name = "QUIZ_TITLE")
    private String quizTitle;
    @Column(name = "QUESTION_TIME")
    private int questionTime;
    @Column(name = "PUBLISH_STATUS")
    private String isPublish = "0";
    @Column(name = "PRIVATE_STATUS")
    private String isPrivate = "0";

    @Column(name = "GROUP_ID")
    private String groupId;
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "GROUP_ID", foreignKey = @ForeignKey(name = "FK_S04ST_ROLE_H07"))
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "GROUP_ID", insertable = false, updatable = false)
//    private TbGroup group;

//    @OneToMany(mappedBy = "quiz")
//    private List<TbQuizQuestion> quizQuestions;
}
