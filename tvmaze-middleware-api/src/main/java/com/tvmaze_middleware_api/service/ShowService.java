package com.tvmaze_middleware_api.service;

import com.tvmaze_middleware_api.dto.tvmaze.TvMazeShow;

public interface ShowService {

    TvMazeShow getShowById(Long showId);
}
