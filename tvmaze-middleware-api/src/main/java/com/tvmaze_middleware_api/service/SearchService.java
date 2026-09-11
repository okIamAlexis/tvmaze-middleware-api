package com.tvmaze_middleware_api.service;

import com.tvmaze_middleware_api.dto.response.ShowSummaryResponse;

import java.util.List;

public interface SearchService {

    List<ShowSummaryResponse> searchShows(String query);
}
