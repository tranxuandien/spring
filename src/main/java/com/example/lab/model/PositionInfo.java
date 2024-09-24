package com.example.lab.model;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "position_info")
@Data
public class PositionInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "position_info")
    private String positionInfo;
    @Column(name = "lab_id")
    private Integer labId;
    @Column(name = "room_id")
    private Integer roomId;
    @Column(name = "ray_id")
    private Integer rayId;

    public PositionInfo() {
    }

    public PositionInfo(Integer id) {
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
        if (!(object instanceof PositionInfo)) {
            return false;
        }
        PositionInfo other = (PositionInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.PositionInfo[ id=" + id + " ]";
    }
    
}
