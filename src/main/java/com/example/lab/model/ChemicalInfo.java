package com.example.lab.model;

import java.io.Serializable;

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
import jakarta.persistence.Transient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "chemical_info")
//@NamedQueries({ @NamedQuery(name = "ChemicalInfo.findAll", query = "SELECT c FROM ChemicalInfo c") })
////		@NamedQuery(name = "ChemicalInfo.findById", query = "SELECT c FROM ChemicalInfo c WHERE c.id = :id"),
//		@NamedQuery(name = "ChemicalInfo.findByName", query = "SELECT c FROM ChemicalInfo c WHERE c.name = :name"),
////		@NamedQuery(name = "ChemicalInfo.findByBrandId", query = "SELECT c FROM ChemicalInfo c WHERE c.brandId = :brandId"),
//		@NamedQuery(name = "ChemicalInfo.findByChemicalType", query = "SELECT c FROM ChemicalInfo c WHERE c.chemicalType = :chemicalType"),
//		@NamedQuery(name = "ChemicalInfo.findByChemicalTypeInfo", query = "SELECT c FROM ChemicalInfo c WHERE c.chemicalTypeInfo = :chemicalTypeInfo"),
//		@NamedQuery(name = "ChemicalInfo.findByDescription", query = "SELECT c FROM ChemicalInfo c WHERE c.description = :description"),
//		@NamedQuery(name = "ChemicalInfo.findByChemicalShpt", query = "SELECT c FROM ChemicalInfo c WHERE c.chemicalShpt = :chemicalShpt"),
//		@NamedQuery(name = "ChemicalInfo.findByOtherInfo", query = "SELECT c FROM ChemicalInfo c WHERE c.otherInfo = :otherInfo"),
////		@NamedQuery(name = "ChemicalInfo.findByChemicalImportUser", query = "SELECT c FROM ChemicalInfo c WHERE c.chemicalImportUser = :chemicalImportUserId"),
//		@NamedQuery(name = "ChemicalInfo.findByPositionId", query = "SELECT c FROM ChemicalInfo c WHERE c.positionId = :positionId"),
//		@NamedQuery(name = "ChemicalInfo.findByImpExpInfo", query = "SELECT c FROM ChemicalInfo c WHERE c.impExpInfo = :impExpInfo"),
//		@NamedQuery(name = "ChemicalInfo.findByChemicalStatus", query = "SELECT c FROM ChemicalInfo c WHERE c.chemicalStatus = :chemicalStatus"),
//		@NamedQuery(name = "ChemicalInfo.findByPurchaseSrc", query = "SELECT c FROM ChemicalInfo c WHERE c.purchaseSrc = :purchaseSrc"),
//		@NamedQuery(name = "ChemicalInfo.findByCreateAt", query = "SELECT c FROM ChemicalInfo c WHERE c.createAt = :createAt"),
//		@NamedQuery(name = "ChemicalInfo.findByUpdateAt", query = "SELECT c FROM ChemicalInfo c WHERE c.updateAt = :updateAt") })
@SecondaryTables({
		@SecondaryTable(name = "chemical_imp_exp", pkJoinColumns = @PrimaryKeyJoinColumn(name = "chemical_id")),
		@SecondaryTable(name = "chemical_inventory", pkJoinColumns = @PrimaryKeyJoinColumn(name = "chemical_id")),
})
public class ChemicalInfo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Basic(optional = false)
	@Column(name = "name")
	private String name;
	@Basic(optional = false)
	@Column(name = "chemical_type")
	private String chemicalType;
	@Basic(optional = false)
	@Column(name = "chemical_type_info")
	private String chemicalTypeInfo;
	@Column(name = "description")
	private String description;
	@Column(name = "chemical_shpt")
	private String chemicalShpt;
	@Column(name = "other_info")
	private String otherInfo;
	@Basic(optional = false)
	@Column(name = "chemical_status")
	private String chemicalStatus;
	@Column(name = "purchase_src")
	private String purchaseSrc;
	@Transient
	private String registerUser;
	
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id")
	private PositionInfo position;
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	private Brand brand;
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "chemical_using_user_id")
//	private UserInfo chemicalUsingUser;
	@Column(name = "code")
	private String code;
	@Column(name = "expired_date")
	private String expiredDate;
	@Column(name = "type1")
	private String type1;
	@Column(name = "type2")
	private String type2;
	@Basic(optional = false)
	@Column(name="is_delete")
	private String isDelete;
	
	@Embedded
	private EmbeddedChemicalImpExp chemicalImpExp;

	@Embedded
	private EmbeddedChemicalInventory chemicalInventory;
	
	public ChemicalInfo() {
		super();
	}

	public ChemicalInfo(Integer id) {
		this.id = id;
	}

	public ChemicalInfo(Integer id, String name, Brand brand, String chemicalType, String chemicalTypeInfo, String chemicalStatus) {
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
		this.code = dto.code;
		this.description = dto.description;
		this.expiredDate = dto.expiredDate;
		this.type1 = dto.type1;
		this.type2 = dto.type2;
		this.otherInfo = dto.otherInfo;
		this.purchaseSrc = dto.purchaseSrc;
		this.chemicalType = dto.chemicalType;
		this.chemicalShpt = dto.chemicalShpt;
		this.chemicalTypeInfo = dto.chemicalTypeInfo;
		this.chemicalStatus = dto.chemicalStatus;
		this.isDelete = "0";//init
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChemicalShpt() {
		return chemicalShpt;
	}

	public void setChemicalShpt(String chemicalShpt) {
		this.chemicalShpt = chemicalShpt;
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

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
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

	public String getRegisterUser() {
		return registerUser;
	}

	public void setRegisterUser(String registerUser) {
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
