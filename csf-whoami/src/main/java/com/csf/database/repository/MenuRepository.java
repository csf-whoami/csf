package com.csf.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csf.database.models.MenuEntity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, String> {
}
