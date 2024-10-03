package com.example.lab.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.lab.dto.request.ChemicalInfoRequestDto;

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
import jakarta.validation.Valid;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "chemical_info")
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
//	@Column(name = "manufactory_quantity")
//	private BigDecimal manufactoryQuantity;
	@Column(name = "chemical_class")
	private String chemicalClass;
	@Column(name = "chemical_class_info")
	private String chemicalClassInfo;
	@Column(name = "other_info")
	private String otherInfo;
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "register_user")
	private User registerUser;
//	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@Column(name = "brand_id")
	private Long brandId;
	@Basic(optional = false)
	@Column(name = "is_delete")
	private String isDelete;

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
		this.brandId = brand.getId();
		this.chemicalType = chemicalType;
		this.chemicalTypeInfo = chemicalTypeInfo;
	}

	public ChemicalInfo(ChemicalInfoRequestDto dto) {
		super();
		this.name = dto.name;
		this.chemicalType = dto.chemicalType;
		this.chemicalTypeInfo = dto.chemicalTypeInfo;
		this.chemicalClass = dto.chemicalClass;
		this.chemicalClassInfo = dto.chemicalClassInfo;
		this.otherInfo = dto.otherInfo;
		this.isDelete = "0";// init
		this.brandId = dto.getBrand();
	}

	public void update(@Valid ChemicalInfoRequestDto dto) {
		this.name = dto.name;
		this.chemicalType = dto.chemicalType;
		this.chemicalTypeInfo = dto.chemicalTypeInfo;
		this.chemicalClass = dto.chemicalClass;
		this.chemicalClassInfo = dto.chemicalClassInfo;
		this.otherInfo = dto.otherInfo;
		this.brandId =dto.brand;
		this.setUpdateAt(LocalDateTime.now());
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
