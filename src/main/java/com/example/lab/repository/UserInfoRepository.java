package com.example.lab.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab.model.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	@Query("SELECT t1 FROM UserInfo t1 INNER JOIN User t2 ON t1.user.id = t2.id AND t2.userName = ?1 AND t2.isActive = true")
	Optional<UserInfo> findByUsername(String registerUser);

}
