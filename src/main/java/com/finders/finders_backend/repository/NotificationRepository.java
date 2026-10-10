package com.finders.finders_backend.repository;

import com.finders.finders_backend.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Get all notifications for a given user - powers the Notifications dashboard page.
    List<Notification> findByUserId(Long userId);

    // Get only unread ones
    List<Notification> findByUserIdAndIsRead(Long userId, Boolean isRead);
}