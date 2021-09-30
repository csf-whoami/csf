package com.csf.database.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "W_TB_QUESTION_TYPE")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class TbQuestionType extends BaseEntity {

	@Column(name = "QUESTION_ID")
	private Long questionId;

	@Column(name = "TYPE_ID")
	private Long typeId;
}
