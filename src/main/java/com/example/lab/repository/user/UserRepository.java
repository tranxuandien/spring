package com.example.lab.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab.dto.response.CommonSelectResponseDto;
import com.example.lab.dto.response.UserInfoResponseDto;
import com.example.lab.dto.response.UserResponseDto;
import com.example.lab.model.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String username);

	Optional<User> findByEmail(String email);
	
	@Query("select t1 from User t1 inner join Token t2 on t1.id = t2.userId "
			+ "where t1.isActive= false and t1.userName = ?1 and t2.token = ?2 ")
	Optional<User> findNotActiveUser(String username, String token);

	@Query("SELECT new com.example.lab.dto.response.UserResponseDto(t1.id,CONCAT(t2.firstName,' ',t2.lastName),t2.studentId) FROM User t1 "
			+ "INNER JOIN UserInfo t2 "
			+ "ON t1.id = t2.user.id "
			+ "WHERE t1.isActive = true ")
	List<UserResponseDto> findAllUser();

	@Query("SELECT t1.email from User t1 WHERE t1.role = ?1 ORDER BY t1.id LIMIT 1 ")
	String getAdminEmail(String role);

	@Query("SELECT new com.example.lab.dto.response.UserInfoResponseDto(t2.id,CONCAT(t1.firstName,' ',t1.lastName),t1.address,CONCAT(t3.firstName,' ',t3.lastName),t2.email,SUBSTR(t2.role,6),t2.isActive) "
			+ "FROM UserInfo t1 "
			+ "INNER JOIN User t2 "
			+ "ON t2.id = t1.user.id "
			+ "LEFT JOIN UserInfo t3 "
			+ "ON t3.user.id = t1.buddy "
			+ "WHERE ?1 IS NULL OR (CONCAT(t1.firstName,' ',t1.lastName) LIKE %?1%) ")
	List<UserInfoResponseDto> findAllUserInfo(String name);

	@Query("SELECT new com.example.lab.dto.response.CommonSelectResponseDto(t1.user.id,CONCAT(t1.firstName,' ',t1.lastName)) "
			+ "FROM UserInfo t1 "
			+ "WHERE t1.user.role = ?1 "
			+ "AND t1.user.isActive = true")
	List<CommonSelectResponseDto> getUsersBuddy(String role);
	
	@Query("SELECT new com.example.lab.dto.response.CommonSelectResponseDto(t1.user.id,CONCAT(t1.firstName,' ',t1.lastName,'-',t1.studentId)) "
			+ "FROM UserInfo t1 "
			+ "WHERE t1.user.role = ?1 "
			+ "AND t1.user.isActive = true")
	List<CommonSelectResponseDto> getUsersStudent(String role);

	@Modifying
	@Transactional
	@Query("UPDATE UserInfo t1 SET t1.buddy = ?1 WHERE t1.user.id IN ?2")
	void buddyRegister(Long buddy, List<Long> users);

	@Modifying
	@Transactional
	@Query("UPDATE User t1 SET t1.isActive = true WHERE t1.id =?1")
	void active(Long id);
	

	@Modifying
	@Transactional
	@Query("UPDATE User t1 SET t1.isActive = false WHERE t1.id =?1")
	void delete(Long id);
}
