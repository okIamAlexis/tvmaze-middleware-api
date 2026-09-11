package com.tvmaze_middleware_api.dto.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TvMazeShow(
        Long id,
        String name,
        String summary,
        List<String> genres,
        TvMazeChannel network,
        TvMazeChannel webChannel
) {
}
