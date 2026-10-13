package com.campus.marketplace.repository;

import com.campus.marketplace.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByProductIdOrderByTimestampAsc(Long productId);
}
