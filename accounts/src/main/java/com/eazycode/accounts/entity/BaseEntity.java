package com.eazycode.accounts.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass // To act as super class for other entities wherever we extend BaseEntity class
@EntityListeners(AuditingEntityListener.class)
//Whenever this entity is saved or updated, let Spring Data JPA’s AuditingEntityListener automatically fill auditing fields like createdBy, createdAt, lastModifiedBy, lastModifiedAt.
public class BaseEntity {

	@CreatedDate
	@Column(updatable=false)// Hibernate will include in INSERT but ignore during UPDATE
	private LocalDateTime createdAt;
	
	@CreatedBy
	@Column(updatable=false)
	private String createdBy;
	
	@LastModifiedDate
	@Column(insertable=false)
	private LocalDateTime updatedAt;  // Hibernate will ignore during INSERT but include during UPDATE
	
	@LastModifiedBy
	@Column(insertable=false)
	private String updatedBy;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "BaseEntity [createdAt=" + createdAt + ", createdBy=" + createdBy + ", updatedAt=" + updatedAt
				+ ", updatedBy=" + updatedBy + "]";
	}
	
	
}
