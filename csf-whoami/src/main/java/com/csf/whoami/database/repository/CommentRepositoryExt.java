package com.csf.whoami.database.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.csf.base.domain.Comment;
import com.csf.whoami.database.CommentGenerator;

@Repository
public class CommentRepositoryExt implements CommentRepository {

    @Override
    public List<Comment> findAll() {
        //simulate data streaming every 1 second.
//        return Flux.interval(Duration.ofSeconds(1))
//                .onBackpressureDrop()
//                .map(this::generateComment)
//                .flatMapIterable(x -> x);
    	return new ArrayList<>();
    }

    private List<Comment> generateComment(long interval) {
        Comment obj = new Comment(
            CommentGenerator.randomAuthor(), 
            CommentGenerator.randomMessage(), 
            CommentGenerator.getCurrentTimeStamp());
        return Arrays.asList(obj);
    }
}
