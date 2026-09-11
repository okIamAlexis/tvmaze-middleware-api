package com.tvmaze_middleware_api.repository;


import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tvmaze_middleware_api.dto.response.CommentGroup;
import com.tvmaze_middleware_api.model.Comment;

import java.util.Collection;
import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByShowId(Long showId);

    @Aggregation(pipeline = {
            "{ '$match': { 'showId': { '$in': ?0 } } }",
            "{ '$group': { '_id': '$showId', 'comments': { '$push': { 'comment': '$comment', 'rating': '$rating' } } } }"
    })
    List<CommentGroup> findCommentGroupsByShowIdIn(Collection<Long> showIds);
}
