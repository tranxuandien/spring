package com.example.lab.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "chemical_inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChemicalInventory extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	@Basic(optional = false)
	@Column(name = "chemical_id")
	private Long chemicalId;
	@Basic(optional = false)
	@Column(name = "quantity")
	private BigDecimal quantity;
	@Basic(optional = false)
	@Column(name = "lot_id")
	private Long lotId;
	@Column(name = "chemical_status")
	private String chemicalStatus;
	@Column(name = "purchase_src")
	private String purchaseSrc;
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id")
	private PositionInfo position;
	@Basic(optional = false)
	@Column(name = "expired_date")
	private String expiredDate;
	@Basic(optional = false)
	@Column(name = "is_delete")
	private String isDelete;
	
	public ChemicalInventory(Long id) {
		this.id = id;
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
