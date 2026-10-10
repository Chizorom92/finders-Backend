package com.finders.finders_backend.repository;

import com.finders.finders_backend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // Gets every message a user sent
    List<Message> findBySenderId(Long senderId);

    // Get every message a user received
    List<Message> findByReceiverId(Long receiverId);
}