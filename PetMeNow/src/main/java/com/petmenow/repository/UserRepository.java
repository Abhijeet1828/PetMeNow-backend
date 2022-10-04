package com.petmenow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petmenow.model.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {

	boolean existsByPhoneNumber(Long phoneNumber);
	
	boolean existsByUserName(String userName);
	
	UserDetails findFirstByUserName(String userName);
	
	UserDetails findFirstById(Long id);
}
