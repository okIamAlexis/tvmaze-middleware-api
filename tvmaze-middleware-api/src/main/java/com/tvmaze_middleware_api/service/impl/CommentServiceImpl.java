package com.tvmaze_middleware_api.service.impl;

import org.springframework.stereotype.Service;

import com.tvmaze_middleware_api.dto.request.CommentRequest;
import com.tvmaze_middleware_api.model.Comment;
import com.tvmaze_middleware_api.repository.CommentRepository;
import com.tvmaze_middleware_api.service.CommentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	private final CommentRepository commentRepository;

	
	@Override
	public void addComment(CommentRequest request) {
		commentRepository.save(new Comment(null, request.showId(), request.comment(), request.rating()));
	}

}
