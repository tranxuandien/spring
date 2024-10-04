package com.example.lab.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(name = "device_using_info")
@Data
public class DeviceUsingInfo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "device_id")
	private int deviceId;
	@Basic(optional = false)
	@Column(name = "start")
	private LocalDateTime start;
	@Basic(optional = false)
	@Column(name = "end")
	private LocalDateTime end;
	@Basic(optional = false)
	@Column(name = "device_status")
	private Boolean deviceStatus;
	@Basic(optional = false)
	@Column(name = "info")
	private String info;
	@Basic(optional = false)
	@Column(name = "user_id")
	private int userId;

	public DeviceUsingInfo() {
	}

	public DeviceUsingInfo(Integer id) {
		this.id = id;
	}

	public DeviceUsingInfo(Integer id, int deviceId, LocalDateTime start, LocalDateTime end, Boolean deviceStatus, String info,
			int userId) {
		this.id = id;
		this.deviceId = deviceId;
		this.start = start;
		this.end = end;
		this.deviceStatus = deviceStatus;
		this.info = info;
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
		if (!(object instanceof DeviceUsingInfo)) {
			return false;
		}
		DeviceUsingInfo other = (DeviceUsingInfo) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.mycompany.mavenproject1.DeviceUsingInfo[ id=" + id + " ]";
	}

}
