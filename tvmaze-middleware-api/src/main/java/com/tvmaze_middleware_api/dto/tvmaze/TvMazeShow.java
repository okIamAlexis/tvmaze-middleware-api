package com.tvmaze_middleware_api.dto.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TvMazeShow(
        Long id,
        String url,
        String name,
        String type,
        String language,
        List<String> genres,
        String status,
        Integer runtime,
        Integer averageRuntime,
        String premiered,
        String ended,
        String officialSite,
        TvMazeSchedule schedule,
        TvMazeRating rating,
        Integer weight,
        TvMazeChannel network,
        TvMazeChannel webChannel,
        TvMazeImage image,
        String summary,
        Long updated
) {
}
