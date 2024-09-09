package com.example.lab.model;

import java.io.Serializable;
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
@Table(name = "lot_info")
@NamedQueries({
    @NamedQuery(name = "LotInfo.findAll", query = "SELECT l FROM LotInfo l"),
    @NamedQuery(name = "LotInfo.findById", query = "SELECT l FROM LotInfo l WHERE l.id = :id"),
    @NamedQuery(name = "LotInfo.findByChemicalId", query = "SELECT l FROM LotInfo l WHERE l.chemicalId = :chemicalId"),
    @NamedQuery(name = "LotInfo.findByLotNo", query = "SELECT l FROM LotInfo l WHERE l.lotNo = :lotNo"),
    @NamedQuery(name = "LotInfo.findByIsImport", query = "SELECT l FROM LotInfo l WHERE l.isImport = :isImport"),
    @NamedQuery(name = "LotInfo.findByCreateAt", query = "SELECT l FROM LotInfo l WHERE l.createAt = :createAt"),
    @NamedQuery(name = "LotInfo.findByUpdateAt", query = "SELECT l FROM LotInfo l WHERE l.updateAt = :updateAt")})
public class LotInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "chemical_id")
    private int chemicalId;
    @Basic(optional = false)
    @Column(name = "lot_no")
    private String lotNo;
    @Basic(optional = false)
    @Column(name = "is_import")
    private short isImport;
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    public LotInfo() {
    }

    public LotInfo(Integer id) {
        this.id = id;
    }

    public LotInfo(Integer id, int chemicalId, String lotNo, short isImport) {
        this.id = id;
        this.chemicalId = chemicalId;
        this.lotNo = lotNo;
        this.isImport = isImport;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public short getIsImport() {
        return isImport;
    }

    public void setIsImport(short isImport) {
        this.isImport = isImport;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
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
        if (!(object instanceof LotInfo)) {
            return false;
        }
        LotInfo other = (LotInfo) object;
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
