package com.csf.whoami.database.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csf.whoami.database.models.Article;


public interface ArticleRepository extends JpaRepository<Article, Integer> {
	@Query("SELECT m FROM Article m WHERE m.id = :title")
	Article findOne(@Param("title") int title);
}
