package com.example.lab.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab.dto.response.UserResponseDto;
import com.example.lab.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String username);

	Optional<User> findByEmail(String email);
	
	@Query("select t1 from User t1 inner join Token t2 on t1.id = t2.userId "
			+ "where t1.isActive= false and t1.userName = ?1 and t2.token = ?2 ")
	Optional<User> findNotActiveUser(String username, String token);

	@Query("SELECT new com.example.lab.dto.response.UserResponseDto(t1.id,t2.firstName,t2.lastName) FROM User t1 "
			+ "INNER JOIN UserInfo t2 "
			+ "ON t1.id = t2.user.id "
			+ "WHERE t1.isActive = true ")
	List<UserResponseDto> findAllUser();

	@Query("SELECT t1.email from User t1 WHERE t1.role = 'ROLE_ADMIN' ORDER BY t1.id LIMIT 1 ")
	String getAdminEmail();
}
