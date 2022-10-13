package com.petmenow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petmenow.model.PetInformation;

@Repository
public interface PetInformationRepository extends JpaRepository<PetInformation, Long> {

	PetInformation findFirstById(Long id);
}
