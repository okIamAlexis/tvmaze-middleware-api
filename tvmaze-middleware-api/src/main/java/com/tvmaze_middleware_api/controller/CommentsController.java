package com.tvmaze_middleware_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tvmaze_middleware_api.dto.request.CommentRequest;
import com.tvmaze_middleware_api.model.Comment;
import com.tvmaze_middleware_api.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentsController {
	
	private final CommentService commentService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addComment(@Valid @RequestBody CommentRequest request) {
		commentService.addComment(request);
	}

}
