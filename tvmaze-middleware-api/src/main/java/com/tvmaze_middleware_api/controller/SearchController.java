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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
@Tag(name = "Search", description = "Busqueda de shows en TVMaze")
public class SearchController {

	private final SearchService searchService;

	@GetMapping("/shows")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Buscar shows por criterio de texto",
			description = "Consulta la API de TVMaze y devuelve id, name, channel, summary, genres "
					+ "y los comentarios guardados para cada show.")
	@ApiResponse(responseCode = "200", description = "Busqueda realizada correctamente")
	@ApiResponse(responseCode = "404", description = "TVMaze no encontro resultados para la busqueda")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	public List<ShowSummaryResponse> getShows(
			@Parameter(description = "Texto de busqueda, ej. 'girls'", required = true)
			@RequestParam("q") String query) {
		return searchService.searchShows(query);
	}

}
