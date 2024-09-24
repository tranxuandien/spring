package com.example.lab.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class Token extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	@Column(unique = true)
	public String token;

	@Builder.Default
	public String tokenType = "Bearer ";

	public boolean revoked;

	public boolean expired;

	public Long userId;

	public Token(String token, String tokenType, boolean revoked, boolean expired, Long userId) {
		super();
		this.token = token;
		this.tokenType = tokenType;
		this.revoked = revoked;
		this.expired = expired;
		this.userId = userId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		return Objects.equals(id, other.id) && Objects.equals(token, other.token)
				&& Objects.equals(userId, other.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, token, userId);
	}
	
}
	