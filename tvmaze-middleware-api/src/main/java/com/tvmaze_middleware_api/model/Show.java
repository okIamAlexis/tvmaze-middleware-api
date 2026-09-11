package com.tvmaze_middleware_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "shows")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Show {
	
	@Id
	Long id;
	// Add other fields can be added here as needed, for example:
	
	
	
	

}
