package com.tvmaze_middleware_api.service.impl;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import com.tvmaze_middleware_api.dto.tvmaze.TvMazeShow;
import com.tvmaze_middleware_api.exceptions.ShowNotFound;
import com.tvmaze_middleware_api.service.ShowService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

	private final RestClient restClient;
	
	@Override
	public TvMazeShow getShowById(Long showId) {
		
		return restClient.get()
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
	
	}

}
