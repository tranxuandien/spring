package com.example.lab.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String username);

	Optional<User> findByEmail(String email);
	
	@Query("select t1 from User t1 inner join Token t2 on t1.id = t2.userId "
			+ "where t1.isActive= false and t1.userName = ?1 and t2.token = ?2 ")
	Optional<User> findNotActiveUser(String username, String token);
}
