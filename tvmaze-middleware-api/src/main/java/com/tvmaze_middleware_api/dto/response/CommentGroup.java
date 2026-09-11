package com.tvmaze_middleware_api.dto.response;

import org.springframework.data.annotation.Id;

import java.util.List;

public record CommentGroup(
        @Id Long id,
        List<CommentSummary> comments
) {
}
