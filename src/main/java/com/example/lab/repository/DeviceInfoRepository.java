package com.example.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab.dto.response.DeviceInfoResponseDto;
import com.example.lab.model.DeviceInfo;

@Repository
public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, Long> {

	@Query("SELECT t1 FROM DeviceInfo t1 "
			+ "WHERE t1.name = ?1 "
			+ "AND t1.isDelete = false ")
	List<DeviceInfoResponseDto> findByDeviceName(String deviceName);

}
