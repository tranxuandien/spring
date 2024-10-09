package com.example.lab.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.security.core.context.SecurityContextHolder;

import com.example.lab.dto.request.DeviceUsingRequestDto;
import com.example.lab.enums.DeviceUsingRegister;

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
	private Long id;
	@Basic(optional = false)
	@Column(name = "device_id")
	private Long deviceId;
	@Basic(optional = false)
	@Column(name = "start")
	private LocalDateTime start;
	@Basic(optional = false)
	@Column(name = "end")
	private LocalDateTime end;
	@Basic(optional = false)
	@Column(name = "device_status")
	private Boolean deviceStatus;
	@Column(name = "info")
	private String info;
	@Basic(optional = false)
	@Column(name = "register_status")
	private String registerStatus;
	@Column(name = "centrifuge_speed")
	private BigDecimal centrifugeSpeed;
	@Basic(optional = false)
	@Column(name = "expect")
	private String expect;
	@Basic(optional = false)
	@Column(name = "user_id")
	private Long userId;
	

	public DeviceUsingInfo() {
	}

	public DeviceUsingInfo(Long id) {
		this.id = id;
	}

	public DeviceUsingInfo(Long id, Long deviceId, LocalDateTime start, LocalDateTime end, Boolean deviceStatus, String info,
			Long userId) {
		this.id = id;
		this.deviceId = deviceId;
		this.start = start;
		this.end = end;
		this.deviceStatus = deviceStatus;
		this.info = info;
		this.userId = userId;
	}

	public DeviceUsingInfo(DeviceUsingRequestDto dto) {
		this.deviceId = dto.getDeviceId();
		this.start = LocalDateTime.parse(dto.getStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.end = LocalDateTime.parse(dto.getEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.deviceStatus = dto.getDeviceStatus();
		this.info = dto.getInfo();
		this.registerStatus = DeviceUsingRegister.Inprogress.getVal();
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.userId = u.getId();
		this.centrifugeSpeed = dto.getCentrifugeSpeed();
		this.expect = dto.getExpect();
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
