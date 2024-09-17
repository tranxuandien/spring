package com.example.lab.model;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "chemical_lot_info")
@NamedQueries({
    @NamedQuery(name = "ChemicalLotInfo.findAll", query = "SELECT l FROM ChemicalLotInfo l"),
    @NamedQuery(name = "ChemicalLotInfo.findById", query = "SELECT l FROM ChemicalLotInfo l WHERE l.id = :id"),
    @NamedQuery(name = "ChemicalLotInfo.findByChemicalId", query = "SELECT l FROM ChemicalLotInfo l WHERE l.chemicalId = :chemicalId"),
    @NamedQuery(name = "ChemicalLotInfo.findByLotNo", query = "SELECT l FROM ChemicalLotInfo l WHERE l.lotNo = :lotNo"),
    @NamedQuery(name = "ChemicalLotInfo.findByIsImport", query = "SELECT l FROM ChemicalLotInfo l WHERE l.isImport = :isImport"),
    @NamedQuery(name = "ChemicalLotInfo.findByCreateAt", query = "SELECT l FROM ChemicalLotInfo l WHERE l.createAt = :createAt"),
    @NamedQuery(name = "ChemicalLotInfo.findByUpdateAt", query = "SELECT l FROM ChemicalLotInfo l WHERE l.updateAt = :updateAt")})
public class ChemicalLotInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "chemical_id")
    private int chemicalId;
    @Basic(optional = false)
    @Column(name = "lot_no")
    private String lotNo;
    @Basic(optional = false)
    @Column(name = "is_import")
    private boolean isImport;
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    public ChemicalLotInfo() {
    }

    public ChemicalLotInfo(Long id) {
        this.id = id;
    }

    public ChemicalLotInfo(Long id, int chemicalId, String lotNo, boolean isImport) {
        this.id = id;
        this.chemicalId = chemicalId;
        this.lotNo = lotNo;
        this.isImport = isImport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getChemicalId() {
        return chemicalId;
    }

    public void setChemicalId(int chemicalId) {
        this.chemicalId = chemicalId;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public boolean getIsImport() {
        return isImport;
    }

    public void setIsImport(boolean isImport) {
        this.isImport = isImport;
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
        if (!(object instanceof ChemicalLotInfo)) {
            return false;
        }
        ChemicalLotInfo other = (ChemicalLotInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.LotInfo[ id=" + id + " ]";
    }
    
}
