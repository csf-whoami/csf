package com.csf.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csf.database.models.EventEntity;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

	@Query(value = "SELECT event"
			+ " FROM EventEntity event"
			+ " WHERE event.email = :email")
	EventEntity fetchEventInfo(@Param("email")String email);
}
