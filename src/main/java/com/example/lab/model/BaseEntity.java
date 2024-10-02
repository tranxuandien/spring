package com.example.lab.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

	@Column(name = "create_at")
	private LocalDateTime createAt;
	@Column(name = "update_at")
	private LocalDateTime updateAt;

	public BaseEntity() {
		super();
		this.createAt = LocalDateTime.now();
		this.updateAt = LocalDateTime.now();
	}


	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

}
