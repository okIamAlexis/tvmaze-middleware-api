package com.tvmaze_middleware_api.service.impl;

import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import com.tvmaze_middleware_api.dto.response.ShowSummaryResponse;
import com.tvmaze_middleware_api.dto.tvmaze.TvMazeSearchResult;
import com.tvmaze_middleware_api.dto.tvmaze.TvMazeShow;
import com.tvmaze_middleware_api.exceptions.ShowNotFound;
import com.tvmaze_middleware_api.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
	
	private final RestClient restClient;
	
	@Override
	public List<ShowSummaryResponse> searchShows(String query) {

		 List<TvMazeSearchResult> searchResult = restClient.get()
				.uri(uriBuilder -> uriBuilder
						.path("/search/shows")
						.queryParam("q", query)
						.build()
						)
				.retrieve()
				.onStatus(HttpStatusCode :: is4xxClientError, (request, response) -> {
					log.error("Client error occurred while searching for shows with query: {}", query);
					throw new ShowNotFound("Show not found for query: " + query);
				})
				.onStatus(HttpStatusCode :: is5xxServerError, (request, response) -> {
					log.error("Server error occurred while searching for shows with query: {}", query);
					throw new RestClientException("TVmaze service not available");
				})
				.body(new ParameterizedTypeReference<List<TvMazeSearchResult>>() {});
		 
		 List<ShowSummaryResponse> showSummaryResponses = searchResult.stream()
				 .map(result -> mapToShowSummaryResponse(result))
				 .toList();
		 
		 return showSummaryResponses; 
						
	}
	
	
	private ShowSummaryResponse mapToShowSummaryResponse(TvMazeSearchResult searchResult) {
		return new ShowSummaryResponse(
				searchResult.show().id(),
				searchResult.show().name(),
				resolveChannelName(searchResult.show()),
				searchResult.show().summary(),
				searchResult.show().genres()
		);
	}
	
	private String resolveChannelName(TvMazeShow show) {
	    if (show.network() != null) {
	        return show.network().name();
	    }
	    if (show.webChannel() != null) {
	        return show.webChannel().name();
	    }
	    return "N/A";
	}

}
