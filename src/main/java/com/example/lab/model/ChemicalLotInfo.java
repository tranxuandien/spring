package com.example.lab.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "chemical_lot_info")
@Data
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
