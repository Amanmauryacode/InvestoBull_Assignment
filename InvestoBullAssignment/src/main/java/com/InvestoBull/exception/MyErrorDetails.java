package com.InvestoBull.exception;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyErrorDetails {


	private String message;
	private String details;
	private LocalDateTime timestamp;
	
	public MyErrorDetails(String message, String details, LocalDateTime timestamp) {
		
		this.message = message;
		this.details = details;
		this.timestamp = timestamp;
	}

	
	
}
