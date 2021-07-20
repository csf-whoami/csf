package com.csf.whoami.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="W_TB_ROLE")
@Where(clause = "DELETED_AT IS NULL")
public class TbRole extends BaseEntity {

	@Column(name = "ROLE_CODE")
    private String code;
    @Column(name = "ROLE_NAME")
    private String name;
}
