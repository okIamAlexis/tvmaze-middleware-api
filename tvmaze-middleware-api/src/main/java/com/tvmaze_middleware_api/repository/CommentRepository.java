package com.tvmaze_middleware_api.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.tvmaze_middleware_api.model.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

}
