package com.example.lab.model;

import java.io.Serializable;

import org.springframework.security.core.context.SecurityContextHolder;

import com.example.lab.dto.request.DeviceRegisterRequestDto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "device_info")
@Data
@AllArgsConstructor
public class DeviceInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "position_id")
    private Long positionId;
    @Column(name = "other_info")
    private String otherInfo;
    @Basic(optional = false)
    @Column(name = "device_status")
    private Boolean deviceStatus;
    @Basic(optional = false)
    @Column(name = "user_id")
    private Long userId;
    @Basic(optional = false)
    @Column(name = "is_delete")
    private Boolean isDelete;

    public DeviceInfo() {
    }

    public DeviceInfo(DeviceRegisterRequestDto dto) {
    	this.name = dto.getName();
    	this.positionId = Long.parseLong(dto.getPosition());
    	this.otherInfo = dto.getOtherInfo();
    	User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	this.userId =u.getId();
    	this.isDelete = false;
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
        if (!(object instanceof DeviceInfo)) {
            return false;
        }
        DeviceInfo other = (DeviceInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.DeviceInfo[ id=" + id + " ]";
    }
    
}
