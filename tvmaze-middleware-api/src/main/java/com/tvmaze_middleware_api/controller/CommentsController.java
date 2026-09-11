package com.tvmaze_middleware_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tvmaze_middleware_api.dto.request.CommentRequest;
import com.tvmaze_middleware_api.model.Comment;
import com.tvmaze_middleware_api.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Comments", description = "Comentarios y calificaciones de shows")
public class CommentsController {

	private final CommentService commentService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Guarda un comentario y la calificacion para un show",
			description = "Recibe showId, comment y rating (0-5). El rating fuera de rango "
					+ "o el comment vacio devuelven 400 Bad Request.")
	@ApiResponse(responseCode = "201", description = "Comentario guardado correctamente")
	@ApiResponse(responseCode = "400", description = "Datos de entrada invalidos")
	public void addComment(@Valid @RequestBody CommentRequest request) {
		commentService.addComment(request);
	}

}
