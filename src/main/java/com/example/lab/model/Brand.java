package com.example.lab.model;

import java.io.Serializable;

import com.example.lab.dto.request.BrandRegisterRequestDto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



/**
 *
 * @author Admin
 */
@Entity
@Table(name = "brand")
@Data
public class Brand  extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "zip_code")
    private int zipCode;

    public Brand() {
    }

    public Brand(Long id) {
        this.id = id;
    }

    public Brand(Long id, String name, int zipCode) {
        this.id = id;
        this.name = name;
        this.zipCode = zipCode;
    }
    
    public Brand(BrandRegisterRequestDto dto) {
    	this.name = dto.getName().trim();
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
