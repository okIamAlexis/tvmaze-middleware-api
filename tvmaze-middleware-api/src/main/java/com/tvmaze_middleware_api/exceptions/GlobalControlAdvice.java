package com.tvmaze_middleware_api.exceptions;

import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
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
