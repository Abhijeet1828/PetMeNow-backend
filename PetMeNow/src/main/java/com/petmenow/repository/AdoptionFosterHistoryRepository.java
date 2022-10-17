package com.petmenow.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petmenow.model.AdoptionFosterHistory;

@Repository
public interface AdoptionFosterHistoryRepository extends JpaRepository<AdoptionFosterHistory, Long> {

	AdoptionFosterHistory findFirstById(Long id);
	
	List<AdoptionFosterHistory> findByOwnerId(Long ownerId);
	
	@Query("SELECT a FROM AdoptionFosterHistory a WHERE a.status = :status AND a.startDate >= :startDate ORDER BY a.startDate ASC")
	List<AdoptionFosterHistory> findByStatusAndStartDateBefore(String status, Date startDate);
}
