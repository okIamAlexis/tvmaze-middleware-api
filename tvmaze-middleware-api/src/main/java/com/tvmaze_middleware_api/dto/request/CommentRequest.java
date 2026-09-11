package com.tvmaze_middleware_api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequest(
		@NotNull(message = "Show ID cannot be null")
		Long showId,
		@NotBlank(message = "Comment cannot be blank")
		String comment,
		@NotNull(message = "Rating cannot be null")
		@Min(value = 0, message = "Rating must be greater than 0")
		@Max(value = 5, message = "Rating must be less than or equal to 5")
		Integer rating
		) {

}
