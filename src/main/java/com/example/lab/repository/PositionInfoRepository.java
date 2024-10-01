package com.example.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab.model.PositionInfo;

@Repository
public interface PositionInfoRepository extends JpaRepository<PositionInfo, Long> {

	List<PositionInfo> findByPositionInfo(String positionInfo);

}
