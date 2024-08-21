package com.example.lab.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "chemical_imp_exp")
@Builder
@NamedQueries({
		@NamedQuery(name = "ChemicalImpExp.findById", query = "SELECT c FROM ChemicalImpExp c WHERE c.id = :id"),
		@NamedQuery(name = "ChemicalImpExp.findByType", query = "SELECT c FROM ChemicalImpExp c WHERE c.type = :type"),
		@NamedQuery(name = "ChemicalImpExp.findByQuantity", query = "SELECT c FROM ChemicalImpExp c WHERE c.quantity = :quantity"),
		@NamedQuery(name = "ChemicalImpExp.findByChemicalId", query = "SELECT c FROM ChemicalImpExp c WHERE c.chemicalId = :chemicalId"),
		@NamedQuery(name = "ChemicalImpExp.findByImpUser", query = "SELECT c FROM ChemicalImpExp c WHERE c.impUser = :impUser"),
		@NamedQuery(name = "ChemicalImpExp.findByExpUser", query = "SELECT c FROM ChemicalImpExp c WHERE c.expUser = :expUser"),
		@NamedQuery(name = "ChemicalImpExp.findByCreateAt", query = "SELECT c FROM ChemicalImpExp c WHERE c.createAt = :createAt"),
		@NamedQuery(name = "ChemicalImpExp.findByUpdateAt", query = "SELECT c FROM ChemicalImpExp c WHERE c.updateAt = :updateAt") })
public class ChemicalImpExp extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "type")
	private String type;
	@Basic(optional = false)
	@Column(name = "quantity")
	private BigDecimal quantity;
	@Basic(optional = false)
	@Column(name = "chemical_id")
	private int chemicalId;
	@Column(name = "imp_user")
	private Long impUser;
	@Column(name = "exp_user")
	private Long expUser;
	
	
	public ChemicalImpExp() {
	}

	public ChemicalImpExp(Integer id) {
		this.id = id;
	}

	public ChemicalImpExp(Integer id, String type, BigDecimal quantity, int chemicalId,Long impUser,Long expUser) {
		this.id = id;
		this.type = type;
		this.quantity = quantity;
		this.chemicalId = chemicalId;
		this.impUser = impUser;
		this.expUser = expUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public int getChemicalId() {
		return chemicalId;
	}

	public void setChemicalId(int chemicalId) {
		this.chemicalId = chemicalId;
	}

	public Long getImpUser() {
		return impUser;
	}

	public void setImpUser(Long impUser) {
		this.impUser = impUser;
	}

	public Long getExpUser() {
		return expUser;
	}

	public void setExpUser(Long expUser) {
		this.expUser = expUser;
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
		if (!(object instanceof ChemicalImpExp)) {
			return false;
		}
		ChemicalImpExp other = (ChemicalImpExp) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.mycompany.mavenproject1.ChemicalImpExp[ id=" + id + " ]";
	}

}
