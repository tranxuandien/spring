package com.example.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab.model.DeviceUsingUsers;

@Repository
public interface DeviceUsingUsersRepository extends JpaRepository<DeviceUsingUsers, Long>{

}
