package com.example.lab.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
@Table(name = "chemical_imp_exp")
public class ChemicalImpExp extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	@Basic(optional = false)
	@Column(name = "type")
	private String type;
	@Basic(optional = false)
	@Column(name = "quantity")
	private BigDecimal quantity;
	@Basic(optional = false)
	@Column(name = "chemical_id")
	private Long chemicalId;
	@Column(name = "imp_user")
	private Long impUser;
	@Column(name = "exp_user")
	private Long expUser;
	@Basic(optional = false)
	@Column(name = "lot_id")
	private Long lotId;
	
	public ChemicalImpExp() {
	}

	public ChemicalImpExp(Long id) {
		this.id = id;
	}

	public ChemicalImpExp(Long id, String type, BigDecimal quantity, Long chemicalId,Long impUser,Long expUser,Long lotId) {
		this.id = id;
		this.type = type;
		this.quantity = quantity;
		this.chemicalId = chemicalId;
		this.impUser = impUser;
		this.expUser = expUser;
		this.lotId = lotId;
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
