package com.example.lab.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "position_info")
@NamedQueries({
    @NamedQuery(name = "PositionInfo.findAll", query = "SELECT p FROM PositionInfo p"),
    @NamedQuery(name = "PositionInfo.findById", query = "SELECT p FROM PositionInfo p WHERE p.id = :id"),
    @NamedQuery(name = "PositionInfo.findByPositionInfo", query = "SELECT p FROM PositionInfo p WHERE p.positionInfo = :positionInfo"),
    @NamedQuery(name = "PositionInfo.findByLabId", query = "SELECT p FROM PositionInfo p WHERE p.labId = :labId"),
    @NamedQuery(name = "PositionInfo.findByRoomId", query = "SELECT p FROM PositionInfo p WHERE p.roomId = :roomId"),
    @NamedQuery(name = "PositionInfo.findByRayId", query = "SELECT p FROM PositionInfo p WHERE p.rayId = :rayId"),
    @NamedQuery(name = "PositionInfo.findByCreateAt", query = "SELECT p FROM PositionInfo p WHERE p.createAt = :createAt"),
    @NamedQuery(name = "PositionInfo.findByUpdateAt", query = "SELECT p FROM PositionInfo p WHERE p.updateAt = :updateAt")})
public class PositionInfo implements Serializable {

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
    @Column(name = "create_at")
    private Date createAt;
    @Column(name = "update_at")
    private Date updateAt;

    public PositionInfo() {
    }

    public PositionInfo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositionInfo() {
        return positionInfo;
    }

    public void setPositionInfo(String positionInfo) {
        this.positionInfo = positionInfo;
    }

    public Integer getLabId() {
        return labId;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRayId() {
        return rayId;
    }

    public void setRayId(Integer rayId) {
        this.rayId = rayId;
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
