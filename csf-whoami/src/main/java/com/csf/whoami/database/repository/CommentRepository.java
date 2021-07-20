package com.csf.whoami.database.repository;

import java.util.List;

import com.csf.whoami.database.dto.Comment;

public interface CommentRepository {
	List<Comment> findAll();
}
