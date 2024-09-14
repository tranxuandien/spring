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
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.SecondaryTables;
import jakarta.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "chemical_info")
@SecondaryTables({
		@SecondaryTable(name = "chemical_imp_exp", pkJoinColumns = @PrimaryKeyJoinColumn(name = "chemical_id")),
		@SecondaryTable(name = "chemical_inventory", pkJoinColumns = @PrimaryKeyJoinColumn(name = "chemical_id")), })
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

	@Embedded
	private EmbeddedChemicalImpExp chemicalImpExp;

	@Embedded
	private EmbeddedChemicalInventory chemicalInventory;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getChemicalType() {
		return chemicalType;
	}

	public void setChemicalType(String chemicalType) {
		this.chemicalType = chemicalType;
	}

	public String getChemicalTypeInfo() {
		return chemicalTypeInfo;
	}

	public void setChemicalTypeInfo(String chemicalTypeInfo) {
		this.chemicalTypeInfo = chemicalTypeInfo;
	}

	public BigDecimal getManufactoryQuantity() {
		return manufactoryQuantity;
	}

	public void setManufactoryQuantity(BigDecimal manufactoryQuantity) {
		this.manufactoryQuantity = manufactoryQuantity;
	}

	public String getChemicalClass() {
		return chemicalClass;
	}

	public void setChemicalClass(String chemicalClass) {
		this.chemicalClass = chemicalClass;
	}

	public String getChemicalClassInfo() {
		return chemicalClassInfo;
	}

	public void setChemicalClassInfo(String chemicalClassInfo) {
		this.chemicalClassInfo = chemicalClassInfo;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getPosition() {
		return position.getPositionInfo();
	}

	public void setPosition(PositionInfo position) {
		this.position = position;
	}

	public String getChemicalStatus() {
		return chemicalStatus;
	}

	public void setChemicalStatus(String chemicalStatus) {
		this.chemicalStatus = chemicalStatus;
	}

	public String getPurchaseSrc() {
		return purchaseSrc;
	}

	public void setPurchaseSrc(String purchaseSrc) {
		this.purchaseSrc = purchaseSrc;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
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

	public UserInfo getRegisterUser() {
		return registerUser;
	}

	public void setRegisterUser(UserInfo registerUser) {
		this.registerUser = registerUser;
	}

	public EmbeddedChemicalImpExp getChemicalImpExp() {
		return chemicalImpExp;
	}

	public void setChemicalImpExp(EmbeddedChemicalImpExp chemicalImpExp) {
		this.chemicalImpExp = chemicalImpExp;
	}

	public EmbeddedChemicalInventory getChemicalInventory() {
		return chemicalInventory;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

}
