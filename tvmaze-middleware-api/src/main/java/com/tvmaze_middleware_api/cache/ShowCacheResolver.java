package com.tvmaze_middleware_api.cache;

import com.tvmaze_middleware_api.dto.tvmaze.TvMazeShow;
import com.tvmaze_middleware_api.exceptions.ShowNotFound;
import com.tvmaze_middleware_api.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
@Slf4j
@RequiredArgsConstructor
public class ShowCacheResolver {

    private final RestClient restClient;
    private final ShowRepository showRepository;

    @Cacheable(value = "shows", key = "#showId")
    public TvMazeShow resolveShow(Long showId) {
    	log.info("Show with ID: {} not found in cache, checking MongoDB", showId);
    	
    	return showRepository.findById(showId)
    	        .map(show -> {
    	            log.info("Show with ID: {} found in database. Caching it now.", showId);
    	            return show;
    	        })
    	        .orElseGet(() -> {
    	            log.info("Show with ID: {} not found in database. Fetching from TVmaze API.", showId);
    	            return fetchAndCacheShow(showId);
    	        });
    }

    private TvMazeShow fetchAndCacheShow(Long showId) {
    	log.info("Looking for information about show with ID: {} in TVmaze API", showId);
        TvMazeShow show = restClient.get()
                .uri("/shows/{id}", showId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    log.error("Client error occurred while fetching show with ID: {}", showId);
                    throw new ShowNotFound("Show not found for ID: " + showId);
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    log.error("Server error occurred while fetching show with ID: {}", showId);
                    throw new RestClientException("TVmaze service not available");
                })
                .body(TvMazeShow.class);

        return showRepository.save(show);
    }
}
