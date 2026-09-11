package com.tvmaze_middleware_api.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.tvmaze_middleware_api.dto.response.ShowSummaryResponse;
import com.tvmaze_middleware_api.service.SearchService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

	private final SearchService searchService;
	
	@GetMapping("/shows")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ShowSummaryResponse> getShows(@RequestParam("q") String query) {
		return searchService.searchShows(query);
	}
	
}
