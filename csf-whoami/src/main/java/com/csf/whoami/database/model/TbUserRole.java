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
@Table(name="W_TB_USER_ROLE")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TbUserRole extends BaseEntity {

    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "ROLE_ID")
    private Long roleId;
}
