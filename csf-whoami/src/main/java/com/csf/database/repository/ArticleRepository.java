package com.csf.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.csf.database.models.Article;


public interface ArticleRepository extends JpaRepository<Article, Integer> {
	@Query("SELECT m FROM Article m WHERE m.id = :title")
	Article findOne(@Param("title") int title);
}
