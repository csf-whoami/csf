/**
 * 
 */
package com.csf.security.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author mba0051
 *
 */
/**
 * The type Base entity.
 */
@MappedSuperclass
abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The Created date.
	 */
	@Column(name = "create_date")
	protected Date createdDate;

	/**
	 * The Updated date.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	protected Date updatedDate;

	/**
	 * The Is deleted.
	 */
	@Column(name = "delflg")
	protected Integer isDeleted;

	/**
	 * Instantiates a new Base entity.
	 *
	 * @param createdDate the created date
	 * @param updatedDate the updated date
	 * @param isDeleted   the is deleted
	 */
	public BaseEntity(Timestamp createdDate, Timestamp updatedDate, Integer isDeleted) {
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.isDeleted = isDeleted;
	}

	/**
	 * Instantiates a new Base entity.
	 */
	public BaseEntity() {
		super();
	}

	/**
	 * On create.
	 */
	@PrePersist
	protected void onCreate() {
		this.createdDate = new Date();
		this.updatedDate = new Date();
		this.isDeleted = 0;
	}

	/**
	 * On update.
	 */
	@PreUpdate
	protected void onUpdate() {
		this.updatedDate = new Date();
	}

	@PreRemove
	protected void onRemove() {
		this.updatedDate = new Date();
	}

	/**
	 * Gets created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets created date.
	 *
	 * @param createdDate the created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets updated date.
	 *
	 * @return the updated date
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * Sets updated date.
	 *
	 * @param updatedDate the updated date
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * Gets is deleted.
	 *
	 * @return the is deleted
	 */
	public Integer getIsDeleted() {
		return isDeleted;
	}

	/**
	 * Sets is deleted.
	 *
	 * @param isDeleted the is deleted
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * Gets serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}