package com.example.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab.dto.response.DeviceInfoMasterResponseDto;
import com.example.lab.dto.response.DeviceInfoResponseDto;
import com.example.lab.model.DeviceInfo;

import jakarta.transaction.Transactional;

@Repository
public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, Long> {

	@Query("SELECT new com.example.lab.dto.response.DeviceInfoMasterResponseDto(t1.id,t1.name) FROM DeviceInfo t1 "
			+ "WHERE t1.isDelete = false "
			+ "AND t1.deviceStatus = true ")
	List<DeviceInfoMasterResponseDto> getListMaster();
	
	@Query("SELECT t1 FROM DeviceInfo t1 "
			+ "WHERE t1.name = ?1 "
			+ "AND t1.isDelete = false "
			+ "AND t1.deviceStatus = true ")
	List<DeviceInfoResponseDto> findByDeviceName(String deviceName);

	@Query("SELECT new com.example.lab.dto.response.DeviceInfoResponseDto(t1.id,t1.name,t2.positionInfo,t1.otherInfo,CONCAT(t3.firstName,' ',t3.lastName),t1.deviceStatus) FROM DeviceInfo t1 "
			+ "LEFT JOIN PositionInfo t2 "
			+ "ON t1.positionId = t2.id "
			+ "LEFT JOIN UserInfo t3 "
			+ "ON t3.user.id = t1.userId "
			+ "WHERE "
			+ "(?1 IS NULL OR t1.name LIKE %?1%) "
			+ "AND t1.isDelete = false "
			+ "AND (?2 IS NULL OR t1.positionId = ?2) ")
	List<DeviceInfoResponseDto> getAllInfo(String name, Long positionId);

	@Modifying
	@Transactional
	@Query("UPDATE DeviceInfo t1 SET t1.isDelete = true WHERE t1.id = ?1")
	void deleteDevice(Long id);
}
