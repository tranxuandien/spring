package com.example.lab.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab.model.DeviceUsingInfo;

@Repository
public interface DeviceUsingInfoRepository extends JpaRepository<DeviceUsingInfo, Long> {

	@Query("SELECT t1 FROM DeviceUsingInfo t1 "
			+ "WHERE t1.id NOT IN (SELECT t.id FROM DeviceUsingInfo t WHERE (t.start > ?3) OR (t.end < ?2)) "
			+ "AND t1.deviceId = ?1 "
			+ "AND t1.userId = ?4 ")
	List<DeviceUsingInfo> getBusyDevices(Long deviceId, LocalDateTime start, LocalDateTime end, Long userId);

}
