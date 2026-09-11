package com.tvmaze_middleware_api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tvmaze_middleware_api.cache.ShowCacheResolver;
import com.tvmaze_middleware_api.dto.response.CommentSummary;
import com.tvmaze_middleware_api.dto.tvmaze.TvMazeShow;
import com.tvmaze_middleware_api.model.Comment;
import com.tvmaze_middleware_api.repository.CommentRepository;
import com.tvmaze_middleware_api.service.ShowService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

	private final ShowCacheResolver showCacheResolver;
	private final CommentRepository commentRepository;

	@Override
	public TvMazeShow getShowById(Long showId) {

		TvMazeShow show = showCacheResolver.resolveShow(showId);

		log.info("Found show with ID: {} in cache or database, now fetching comments", showId);
		List<CommentSummary> comments = commentRepository.findByShowId(showId).stream()
				.map(this::toCommentSummary)
				.toList();

		return show.withComments(comments);
	}

	private CommentSummary toCommentSummary(Comment comment) {
		return new CommentSummary(comment.comment(), comment.rating());
	}

}
