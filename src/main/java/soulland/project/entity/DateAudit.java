package soulland.project.entity;

import java.io.Serializable;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

@MappedSuperclass
public abstract class DateAudit implements Serializable{

	private static final long serialVersionUID = 1L;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	@CreatedDate
	private LocalDateTime createDate;

	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	
}
