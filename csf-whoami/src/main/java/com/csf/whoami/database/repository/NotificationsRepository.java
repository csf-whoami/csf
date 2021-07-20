package com.csf.whoami.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.whoami.database.model.TbNotifications;

public interface NotificationsRepository extends JpaRepository<TbNotifications, String> {
}
