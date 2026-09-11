package com.tvmaze_middleware_api.exceptions;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
