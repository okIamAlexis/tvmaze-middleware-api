package com.tvmaze_middleware_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "comments")
public record Comment(
        @Id String id,
        Long showId,
        String comment,
        Integer rating
) {}

