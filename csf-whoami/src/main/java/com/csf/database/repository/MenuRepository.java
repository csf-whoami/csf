package com.csf.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csf.database.models.TbMenu;

@Repository
public interface MenuRepository extends JpaRepository<TbMenu, String> {
}
