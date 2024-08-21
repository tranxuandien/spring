package com.example.lab.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "user_info")
@NamedQueries({
    @NamedQuery(name = "UserInfo.findAll", query = "SELECT u FROM UserInfo u"),
    @NamedQuery(name = "UserInfo.findById", query = "SELECT u FROM UserInfo u WHERE u.id = :id"),
    @NamedQuery(name = "UserInfo.findByName", query = "SELECT u FROM UserInfo u WHERE u.name = :name"),
    @NamedQuery(name = "UserInfo.findByAddress", query = "SELECT u FROM UserInfo u WHERE u.address = :address"),
    @NamedQuery(name = "UserInfo.findByCreateAt", query = "SELECT u FROM UserInfo u WHERE u.createAt = :createAt"),
    @NamedQuery(name = "UserInfo.findByUpdateAt", query = "SELECT u FROM UserInfo u WHERE u.updateAt = :updateAt"),
    @NamedQuery(name = "UserInfo.findByEmail", query = "SELECT u FROM UserInfo u WHERE u.email = :email")})
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name ="user_id")
    private User user;
    @Column(name = "create_at")
    private Date createAt;
    @Column(name = "update_at")
    private Date updateAt;

    public UserInfo() {
    }

    public UserInfo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(object instanceof UserInfo)) {
            return false;
        }
        UserInfo other = (UserInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.UserInfo[ id=" + id + " ]";
    }
    
}
