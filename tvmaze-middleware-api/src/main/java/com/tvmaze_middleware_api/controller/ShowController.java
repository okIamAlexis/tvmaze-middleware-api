package com.tvmaze_middleware_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tvmaze_middleware_api.dto.tvmaze.TvMazeShow;
import com.tvmaze_middleware_api.service.ShowService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shows")
@RequiredArgsConstructor
@Tag(name = "Shows", description = "Consulta de shows por id, con cache y comentarios")
public class ShowController {

	private final ShowService showService;

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Obtener un show por id",
			description = "Valida primero un cache en memoria (Caffeine) y luego en MongoDB; "
					+ "si no esta en ninguno, consulta la API de TVMaze y lo guarda. "
					+ "Incluye el arreglo comments con los comentarios guardados para el show.")
	@ApiResponse(responseCode = "200", description = "Show encontrado")
	@ApiResponse(responseCode = "400", description = "El id no es un numero valido")
	@ApiResponse(responseCode = "404", description = "No existe un show con ese id en TVMaze")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public TvMazeShow getShowById(
			@Parameter(description = "Id del show en TVMaze", example = "169", required = true)
			@PathVariable("id") Long id) {
		return showService.getShowById(id);
	}

}
