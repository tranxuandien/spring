package com.example.lab.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

	@Column(name = "create_at")
	private LocalDate createAt;
	@Column(name = "update_at")
	private LocalDate updateAt;

	public BaseEntity() {
		super();
		this.createAt = LocalDate.now();
		this.updateAt = LocalDate.now();
	}


	public LocalDate getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDate createAt) {
		this.createAt = createAt;
	}

	public LocalDate getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDate updateAt) {
		this.updateAt = updateAt;
	}

}
