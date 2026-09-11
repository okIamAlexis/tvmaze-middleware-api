package com.tvmaze_middleware_api.service;

import com.tvmaze_middleware_api.dto.request.CommentRequest;
import com.tvmaze_middleware_api.model.Comment;

public interface CommentService {
	
	 void addComment(CommentRequest request);

}
