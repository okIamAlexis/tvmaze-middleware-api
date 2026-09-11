package com.tvmaze_middleware_api.dto.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TvMazeImage(
        String medium,
        String original
) {
}
