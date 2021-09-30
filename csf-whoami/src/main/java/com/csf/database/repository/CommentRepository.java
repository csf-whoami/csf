package com.csf.database.repository;

import java.util.List;

import com.csf.base.domain.Comment;

public interface CommentRepository {
	List<Comment> findAll();
}
