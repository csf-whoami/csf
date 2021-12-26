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
@Table(name = "W_TB_MENU")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntity extends BaseEntity {

    private static final long serialVersionUID = 848359309948808139L;

    @Column(name="MENU_NAME")
    private String menuName              = null;
    @Column(name="LINK_SCREEN")
    private String linkScreen            = null;
    @Column(name="LOCALE")
    private String locale                = null;
    @Column(name = "PARENT_MENU")
    private Long parentMenu              = null;
}
