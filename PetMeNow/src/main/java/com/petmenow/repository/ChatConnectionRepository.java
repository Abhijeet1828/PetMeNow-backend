package com.petmenow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petmenow.model.ChatConnection;

@Repository
public interface ChatConnectionRepository extends JpaRepository<ChatConnection, Long> {
	
	@Query("SELECT c FROM ChatConnection c WHERE (c.firstUserId = :firstUserId AND c.secondUserId = :secondUserId) OR (c.firstUserId = :secondUserId AND c.secondUserId = :firstUserId)")
	List<ChatConnection> fetchConnectionExistForPair(Long firstUserId, Long secondUserId);

	@Query("SELECT c FROM ChatConnection c WHERE c.firstUserId = :userId OR c.secondUserId = :userId ORDER BY c.updatedTimestamp DESC")
	List<ChatConnection> fetchUserChatConnections(Long userId);
}
