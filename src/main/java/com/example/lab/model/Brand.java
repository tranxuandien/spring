package com.example.lab.model;

import java.io.Serializable;
import java.util.Date;


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
@Table(name = "brand")
//@NamedQueries({
//    @NamedQuery(name = "Brand.findAll", query = "SELECT b FROM Brand b"),
//    @NamedQuery(name = "Brand.findById", query = "SELECT b FROM Brand b WHERE b.id = :id"),
//    @NamedQuery(name = "Brand.findByName", query = "SELECT b FROM Brand b WHERE b.name = :name"),
//    @NamedQuery(name = "Brand.findByZipCode", query = "SELECT b FROM Brand b WHERE b.zipCode = :zipCode"),
//    @NamedQuery(name = "Brand.findByCreateAt", query = "SELECT b FROM Brand b WHERE b.createAt = :createAt"),
//    @NamedQuery(name = "Brand.findByUpdateAt", query = "SELECT b FROM Brand b WHERE b.updateAt = :updateAt")})
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "zip_code")
    private int zipCode;
    @Basic(optional = false)
    @Column(name = "create_at")
    private Date createAt;
    @Basic(optional = false)
    @Column(name = "update_at")
    private Date updateAt;

    public Brand() {
    }

    public Brand(Integer id) {
        this.id = id;
    }

    public Brand(Integer id, String name, int zipCode, Date createAt, Date updateAt) {
        this.id = id;
        this.name = name;
        this.zipCode = zipCode;
        this.createAt = createAt;
        this.updateAt = updateAt;
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

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
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
        if (!(object instanceof Brand)) {
            return false;
        }
        Brand other = (Brand) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.Brand[ id=" + id + " ]";
    }
    
}
