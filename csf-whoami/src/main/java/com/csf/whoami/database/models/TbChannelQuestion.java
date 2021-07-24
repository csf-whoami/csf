package com.csf.whoami.database.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "W_TB_CHANNEL_QUESTION")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class TbChannelQuestion extends BaseEntity {

    @Column(name = "CHANNEL_ID")
    private Long channelId;

    @Column(name = "QUESTION_ID")
    private Long questionId;
}
