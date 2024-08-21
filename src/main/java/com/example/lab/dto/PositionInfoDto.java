package com.example.lab.dto;

import java.util.Date;

public class PositionInfoDto {

	private Integer id;
	private String positionInfo;
	private Integer labId;
	private Integer roomId;
	private Integer rayId;
	private Date createAt;
	private Date updateAt;

	public PositionInfoDto(Integer id, String positionInfo) {
		this.id = id;
		this.positionInfo = positionInfo;
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

	
}
