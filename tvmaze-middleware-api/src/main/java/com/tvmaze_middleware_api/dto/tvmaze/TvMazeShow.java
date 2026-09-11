package com.tvmaze_middleware_api.dto.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tvmaze_middleware_api.dto.response.CommentSummary;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "shows")
public record TvMazeShow(
        @Id
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
        Long updated,
      
        // TODO: 60s es temporal para probar el TTL, volver a 600 (10 min) despues.
        @Indexed(expireAfterSeconds = 60)
        Instant cachedAt,
        @Transient List<CommentSummary> comments
) {

    public TvMazeShow withComments(List<CommentSummary> comments) {
        return new TvMazeShow(id, url, name, type, language, genres, status, runtime,
                averageRuntime, premiered, ended, officialSite, schedule, rating, weight,
                network, webChannel, image, summary, updated, cachedAt, comments);
    }

    public TvMazeShow withCachedAt(Instant cachedAt) {
        return new TvMazeShow(id, url, name, type, language, genres, status, runtime,
                averageRuntime, premiered, ended, officialSite, schedule, rating, weight,
                network, webChannel, image, summary, updated, cachedAt, comments);
    }
}
