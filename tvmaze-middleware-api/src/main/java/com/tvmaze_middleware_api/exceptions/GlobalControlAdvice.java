package com.tvmaze_middleware_api.exceptions;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalControlAdvice {
	
	//NOT FOUND
	@ExceptionHandler(ShowNotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ProblemDetail handleShowNotFound(ShowNotFound ex) {
		
		
		log.error("Show not found: {}", ex.getMessage());
		ProblemDetail problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
		problemDetails.setTitle("Show Not Found");
		return problemDetails;
	
	}
	
	//Validations
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                "Validation failed in one or more fields"
                );

        problemDetail.setTitle("Validation Error");
        problemDetail.setProperty("Timestamp", Instant.now());

        Map<String, String> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                error -> {
                    errorMap.put(error.getField(), error.getDefaultMessage());
                }
        );

        problemDetail.setProperty("errors", errorMap);

        return problemDetail;
    }


	//Bad request - path/query param con tipo invalido (ej. /shows/abc en vez de /shows/169)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ProblemDetail handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
		log.error("Invalid parameter type: {}", ex.getMessage());
		String detail = "El valor '" + ex.getValue() + "' no es valido para el parametro '" + ex.getName() + "'";
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
		problemDetail.setTitle("Invalid Parameter");
		problemDetail.setProperty("Timestamp", Instant.now());
		return problemDetail;
	}

	//Bad request - falta un parametro requerido (ej. /search/shows sin ?q=)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ProblemDetail handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
		log.error("Missing request parameter: {}", ex.getMessage());
		String detail = "El parametro requerido '" + ex.getParameterName() + "' no fue enviado";
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
		problemDetail.setTitle("Missing Parameter");
		problemDetail.setProperty("Timestamp", Instant.now());
		return problemDetail;
	}

	//Internal_Errors
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception ex){


    	log.error("Internal server error: {}", ex.getMessage());
    	ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred. Please try again later.");
    	
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setProperty("Timestamp", Instant.now());

        return problemDetail;

    }
}
