package com.example.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab.model.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

}
