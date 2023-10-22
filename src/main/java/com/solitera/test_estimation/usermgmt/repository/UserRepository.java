package com.solitera.test_estimation.usermgmt.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.solitera.test_estimation.usermgmt.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
	
	@Query("SELECT c FROM UserModel c Where c.email = :email")
	Optional<UserModel> findByEmail(String email);

	@Query("SELECT c FROM UserModel c Where c.name = :username")
	Optional<UserModel> findByUsername(String username);

	@Query("SELECT c FROM UserModel c Where c.token = :token")
	Optional<UserModel> findByToken(String token);
	
}
