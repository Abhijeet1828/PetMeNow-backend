package com.petmenow.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petmenow.model.PetBreedMaster;

@Repository
public interface PetBreedMasterRepository extends JpaRepository<PetBreedMaster, Long> {

	List<PetBreedMaster> findAllByPetTypeIdAndPetBreedContainingIgnoreCase(Long petTypeId, String petBreed,
			Pageable pageable);
}
