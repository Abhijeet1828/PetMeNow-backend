package com.petmenow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petmenow.model.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {

}
