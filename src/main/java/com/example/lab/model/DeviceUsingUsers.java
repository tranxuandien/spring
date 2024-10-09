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
@Table(name = "device_using_users")
@Data
@AllArgsConstructor
public class DeviceUsingUsers extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "device_using_id")
    private Long deviceUsingId;
    @Basic(optional = false)
    @Column(name = "user_id")
    private Long userId;

    public DeviceUsingUsers() {
    }
    
    public DeviceUsingUsers(Long userId, Long deviceUsingId) {
    	this.deviceUsingId = deviceUsingId;
    	this.userId = userId;
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
        if (!(object instanceof DeviceUsingUsers)) {
            return false;
        }
        DeviceUsingUsers other = (DeviceUsingUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.DeviceUsingUsers[ id=" + id + " ]";
    }

	
}

