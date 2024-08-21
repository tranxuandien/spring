package com.example.lab.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "user")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
		@NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :username"),
		@NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
		@NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role"),
		@NamedQuery(name = "User.findByCreateAt", query = "SELECT u FROM User u WHERE u.createAt = :createAt"),
		@NamedQuery(name = "User.findByUpdateAt", query = "SELECT u FROM User u WHERE u.updateAt = :updateAt") })
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Basic(optional = false)
	@Column(name = "username")
	private String userName;
	@Basic(optional = false)
	@Column(name = "password")
	private String password;
	@Column(name = "role")
	private String role;
	@Basic(optional = false)
	@Column(name = "create_at")
	private Date createAt;
	@Basic(optional = false)
	@Column(name = "update_at")
	private Date updateAt;

	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserInfo userInfo;

	public User() {
	}

	public User(Long id) {
		this.id = id;
	}

	public User(Long id, String userName, String password, Date createAt, Date updateAt) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
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
