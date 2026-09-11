package com.tvmaze_middleware_api.dto.response;

import java.util.List;

public record ShowSummaryResponse(
        Long id,
        String name,
        String channel,
        String summary,
        List<String> genres
) {
}
