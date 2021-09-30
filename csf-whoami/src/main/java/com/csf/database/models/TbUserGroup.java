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
@Table(name = "W_TB_USER_GROUP")
@Where(clause = "DELETED_AT IS NULL")
@Getter @Setter
public class TbUserGroup extends BaseEntity {

	@Column(name = "USER_ID")
	private String userId;
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
//	@ManyToOne(optional = false)
//	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_S01DT_USER_H05"))
//	private TbAccount user;

	@Column(name = "GROUP_ID")
	private String groupId;
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "GROUP_ID", insertable = false, updatable = false)
//	@ManyToOne(optional = false)
//	@JoinColumn(name = "GROUP_ID", foreignKey = @ForeignKey(name = "FK_S04ST_ROLE_H05"))
//	private TbGroup group;
}
