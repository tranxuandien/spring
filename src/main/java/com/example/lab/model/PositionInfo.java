package com.example.lab.model;

import java.io.Serializable;

import com.example.lab.dto.request.PositionRegisterRequestDto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "position_info")
@Data
@NoArgsConstructor
public class PositionInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "position_info")
    private String positionInfo;
    @Column(name = "lab")
    private String lab;
    @Column(name = "room")
    private String room;
    @Column(name = "ray")
    private String ray;

    public PositionInfo(Long id) {
        this.id = id;
    }
    
    public PositionInfo(PositionRegisterRequestDto dto) {
		this.lab = dto.getLab().trim();
		this.room = dto.getRoom() == null ? null : dto.getRoom().trim();
		this.ray = dto.getRay() == null ? null : dto.getRay().trim();
		this.positionInfo = this.lab;
		if (this.room != null)
			this.positionInfo = this.positionInfo + "-" + this.room;
		if (this.ray != null)
			this.positionInfo = this.positionInfo + "-" + this.ray;
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
