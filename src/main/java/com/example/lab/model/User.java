package com.example.lab.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.lab.dto.request.UserRegisterRequest;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "user")
@Data
public class User extends BaseEntity implements UserDetails  {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	@Column(name = "id")
	private Long id;
	@Basic(optional = false)
	@Setter
	@Column(name = "username")
	private String userName;
	@Basic(optional = false)
	@Getter
	@Setter
	@Column(name = "password")
	private String password;
	@Column(name = "role")
	@Getter
	@Setter
	private String role;

	@Column(name = "email")
	@Basic(optional = false)
	@Getter
	@Setter
	private String email;

	@Column(name = "is_active")
	@Basic(optional = false)
	@Getter
	@Setter
	private Boolean isActive;
	

	public User() {
	}

	public User(Long id) {
		this.id = id;
	}

	public User(Long id, String userName, String password) {
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	public User(UserRegisterRequest request) {
		this.setUserName(request.getUsername());
		this.setRole(request.getRole());// default
		this.setEmail(request.getEmail());
		this.setIsActive(false);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.mycompany.mavenproject1.User[ id=" + id + " ]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getUsername() {
		return userName;
	}
}
