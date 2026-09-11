package com.tvmaze_middleware_api.repository;

import com.tvmaze_middleware_api.dto.tvmaze.TvMazeShow;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShowRepository extends MongoRepository<TvMazeShow, Long> {
}
