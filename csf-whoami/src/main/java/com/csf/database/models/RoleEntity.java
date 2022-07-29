package com.csf.database.models;

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
@Table(name="W_ROLES")
@Where(clause = "DELETED_AT IS NULL")
public class RoleEntity extends BaseEntity {

	private static final long serialVersionUID = 379157780539861343L;

	@Column(name = "CODE")
    private String code;
    @Column(name = "NAME")
    private String name;
}
