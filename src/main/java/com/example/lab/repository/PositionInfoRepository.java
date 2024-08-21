package com.example.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab.model.PositionInfo;

public interface PositionInfoRepository extends JpaRepository<PositionInfo, Long> {

}
