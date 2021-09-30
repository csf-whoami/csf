package com.csf.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.database.models.TbNotifications;

public interface NotificationsRepository extends JpaRepository<TbNotifications, String> {
}
