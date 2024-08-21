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

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "chemical_inventory")
@NamedQueries({ @NamedQuery(name = "ChemicalInventory.findAll", query = "SELECT c FROM ChemicalInventory c"),
		@NamedQuery(name = "ChemicalInventory.findById", query = "SELECT c FROM ChemicalInventory c WHERE c.id = :id"),
		@NamedQuery(name = "ChemicalInventory.findByChemicalId", query = "SELECT c FROM ChemicalInventory c WHERE c.chemicalId = :chemicalId"),
		@NamedQuery(name = "ChemicalInventory.findByQuantity", query = "SELECT c FROM ChemicalInventory c WHERE c.quantity = :quantity"),
		@NamedQuery(name = "ChemicalInventory.findByCreateAt", query = "SELECT c FROM ChemicalInventory c WHERE c.createAt = :createAt"),
		@NamedQuery(name = "ChemicalInventory.findByUpdateAt", query = "SELECT c FROM ChemicalInventory c WHERE c.updateAt = :updateAt") })
public class ChemicalInventory extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "chemical_id")
	private Integer chemicalId;
	@Basic(optional = false)
	@Column(name = "quantity")
	private BigDecimal quantity;

	public ChemicalInventory() {
	}

	public ChemicalInventory(Integer id) {
		this.id = id;
	}

	public ChemicalInventory(Integer id, Integer chemicalId, BigDecimal quantity) {
		this.id = id;
		this.chemicalId = chemicalId;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChemicalId() {
		return chemicalId;
	}

	public void setChemicalId(Integer chemicalId) {
		this.chemicalId = chemicalId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
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
		if (!(object instanceof ChemicalInventory)) {
			return false;
		}
		ChemicalInventory other = (ChemicalInventory) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.mycompany.mavenproject1.ChemicalInventory[ id=" + id + " ]";
	}

}
