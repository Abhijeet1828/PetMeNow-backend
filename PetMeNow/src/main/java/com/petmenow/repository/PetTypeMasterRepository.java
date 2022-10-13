package com.petmenow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petmenow.model.PetTypeMaster;

@Repository
public interface PetTypeMasterRepository extends JpaRepository<PetTypeMaster, Long> {

}
