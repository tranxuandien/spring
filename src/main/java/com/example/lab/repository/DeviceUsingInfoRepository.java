package com.example.lab.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab.dto.response.DeviceUsingInfoResponseDto;
import com.example.lab.model.DeviceUsingInfo;

@Repository
public interface DeviceUsingInfoRepository extends JpaRepository<DeviceUsingInfo, Long> {

	@Query("SELECT t1 FROM DeviceUsingInfo t1 "
			+ "WHERE t1.id NOT IN (SELECT t.id FROM DeviceUsingInfo t WHERE (t.start > ?3) OR (t.end < ?2)) "
			+ "AND t1.deviceId = ?1 "
//			+ "AND t1.userId = ?4 "
			+ "AND t1.registerStatus = ?4 ")
	List<DeviceUsingInfo> getBusyDevices(Long deviceId, LocalDateTime start, LocalDateTime end, String status);

	@Query("SELECT new com.example.lab.dto.response.DeviceUsingInfoResponseDto(t1.id,t2.name,t1.start,t1.end,t1.registerStatus,t1.info,CONCAT(t6.firstName,' ',t6.lastName),t1.centrifugeSpeed,GROUP_CONCAT(CONCAT(t5.firstName,' ',t5.lastName)),t1.expect,CONCAT(t3.firstName,' ',t3.lastName)) "
			+ "FROM DeviceUsingInfo t1 "
			+ "INNER JOIN DeviceInfo t2 "
			+ "ON t2.id = t1.deviceId "
			+ "INNER JOIN UserInfo t3 "
			+ "ON t3.user.id= t1.userId "
			+ "LEFT JOIN DeviceUsingUsers t4 "
			+ "ON t4.deviceUsingId = t1.id "
			+ "LEFT JOIN UserInfo t5 "
			+ "ON t5.user.id = t4.userId "
			+ "LEFT JOIN UserInfo t6 "
			+ "ON t6.user.id = t3.buddy "
			+ "WHERE ?1 IS NULL OR (t1.userId = ?1) "
			+ "GROUP BY t1.id "
			+ "ORDER BY t1.registerStatus ")
	List<DeviceUsingInfoResponseDto> getUsingDevicesByUser(Long userId);

	@Query("SELECT new com.example.lab.dto.response.DeviceUsingInfoResponseDto(t1.id,t2.name,t1.start,t1.end,t1.registerStatus,t1.info,null,null,null,null,CONCAT(t3.firstName,' ',t3.lastName)) "
			+ "FROM DeviceUsingInfo t1 "
			+ "INNER JOIN DeviceInfo t2 "
			+ "ON t2.id = t1.deviceId "
			+ "INNER JOIN UserInfo t3 "
			+ "ON t3.user.id= t1.userId "
			+ "LEFT JOIN DeviceUsingUsers t4 "
			+ "ON t4.deviceUsingId = t1.id "
			+ "LEFT JOIN UserInfo t5 "
			+ "ON t5.user.id = t4.userId "
			+ "WHERE (?1 IS NULL OR (t1.deviceId = ?1)) "
			+ "AND t1.registerStatus = ?2 "
			+ "GROUP BY t1.id "
			+ "ORDER BY t1.start DESC")
	List<DeviceUsingInfoResponseDto> getUsingDevicesByDevice(Long deviceId,String status);
}
