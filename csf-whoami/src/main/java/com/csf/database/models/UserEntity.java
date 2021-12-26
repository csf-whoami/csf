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
@Table(name = "W_TB_USER")
@Where(clause = "DELETED_AT IS NULL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = 5939621114505756670L;

    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NOTE")
    private String note;
}
