package com.petmenow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petmenow.model.ChatHistory;

@Repository
public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {

	List<ChatHistory> findAllByChannelNameOrderByMessageTimeDesc(String channelName);

}
