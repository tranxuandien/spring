package com.example.lab.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.example.lab.dto.ChemicalInfoDto;
import com.example.lab.model.chemical.EmbeddedChemicalImpExp;
import com.example.lab.model.chemical.EmbeddedChemicalInventory;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "chemical_info")
//@SecondaryTables({
//		@SecondaryTable(name = "chemical_imp_exp", pkJoinColumns = @PrimaryKeyJoinColumn(name = "chemical_id")),
//		@SecondaryTable(name = "chemical_inventory", pkJoinColumns = @PrimaryKeyJoinColumn(name = "chemical_id")), })
@Data
public class ChemicalInfo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Basic(optional = false)
	@Column(name = "name")
	private String name;
	@Basic(optional = false)
	@Column(name = "chemical_type")
	private String chemicalType;
	@Basic(optional = false)
	@Column(name = "chemical_type_info")
	private String chemicalTypeInfo;
	@Column(name = "manufactory_quantity")
	private BigDecimal manufactoryQuantity;
	@Column(name = "chemical_class")
	private String chemicalClass;
	@Column(name = "chemical_class_info")
	private String chemicalClassInfo;
	@Column(name = "other_info")
	private String otherInfo;
	@Basic(optional = false)
	@Column(name = "chemical_status")
	private String chemicalStatus;
	@Column(name = "purchase_src")
	private String purchaseSrc;
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "register_user")
	private UserInfo registerUser;
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id")
	private PositionInfo position;
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	private Brand brand;
	@Column(name = "expired_date")
	private String expiredDate;
	@Basic(optional = false)
	@Column(name = "is_delete")
	private String isDelete;

//	@Embedded
//	private EmbeddedChemicalImpExp chemicalImpExp;
//
//	@Embedded
//	private EmbeddedChemicalInventory chemicalInventory;

	public ChemicalInfo() {
		super();
	}

	public ChemicalInfo(Long id) {
		this.id = id;
	}

	public ChemicalInfo(Long id, String name, Brand brand, String chemicalType, String chemicalTypeInfo,
			String chemicalStatus) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.chemicalType = chemicalType;
		this.chemicalTypeInfo = chemicalTypeInfo;
		this.chemicalStatus = chemicalStatus;
	}

	public ChemicalInfo(ChemicalInfoDto dto) {
		super();
		this.name = dto.name;
		this.manufactoryQuantity = dto.manufactoryQuantity;
		this.expiredDate = dto.expiredDate;
		this.otherInfo = dto.otherInfo;
		this.purchaseSrc = dto.purchaseSrc;
		this.chemicalType = dto.chemicalType;
		this.chemicalClass = dto.chemicalClass;
		this.chemicalClassInfo = dto.chemicalClassInfo;
		this.chemicalTypeInfo = dto.chemicalTypeInfo;
		this.chemicalStatus = dto.chemicalStatus;
		this.isDelete = "0";// init
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
		if (!(object instanceof ChemicalInfo)) {
			return false;
		}
		ChemicalInfo other = (ChemicalInfo) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.mycompany.mavenproject1.ChemicalInfo[ id=" + id + " ]";
	}

}
