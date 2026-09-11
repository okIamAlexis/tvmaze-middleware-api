package com.tvmaze_middleware_api.dto.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TvMazeSearchResult(
        Double score,
        TvMazeShow show
) {
}
