package com.example.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab.dto.response.DeviceUsingInfoResponseDto;
import com.example.lab.model.DeviceUsingInfo;

@Repository
public interface DeviceUsingRepository extends JpaRepository<DeviceUsingInfo, Long> {

	@Query("SELECT new com.example.lab.dto.response.DeviceUsingInfoResponseDto(t2.name,t1.start,t1.end,t1.deviceStatus,t1.info,CONCAT(t3.firstName,' ',t3.lastName)) "
			+ "FROM DeviceUsingInfo t1 "
			+ "INNER JOIN DeviceInfo t2 "
			+ "ON t2.id = t1.deviceId "
			+ "INNER JOIN UserInfo t3 "
			+ "ON t3.user.id= t1.userId "
			+ "WHERE ?1 IS NULL OR (t1.userId = ?1) "
			+ "ORDER BY t1.start DESC")
	List<DeviceUsingInfoResponseDto> getUsingDevicesByUser(Long userId);
}
