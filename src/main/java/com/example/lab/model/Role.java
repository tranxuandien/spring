package com.example.lab.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "role")
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
    @NamedQuery(name = "Role.findById", query = "SELECT r FROM Role r WHERE r.id = :id"),
    @NamedQuery(name = "Role.findByRoleName", query = "SELECT r FROM Role r WHERE r.roleName = :roleName"),
    @NamedQuery(name = "Role.findByCreateAt", query = "SELECT r FROM Role r WHERE r.createAt = :createAt"),
    @NamedQuery(name = "Role.findByUpdateAt", query = "SELECT r FROM Role r WHERE r.updateAt = :updateAt")})
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "role_name")
    private String roleName;
    @Basic(optional = false)
    @Column(name = "create_at")
    private Date createAt;
    @Basic(optional = false)
    @Column(name = "update_at")
    private Date updateAt;

    public Role() {
    }

    public Role(Integer id) {
        this.id = id;
    }

    public Role(Integer id, String roleName, Date createAt, Date updateAt) {
        this.id = id;
        this.roleName = roleName;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.Role[ id=" + id + " ]";
    }
    
}
