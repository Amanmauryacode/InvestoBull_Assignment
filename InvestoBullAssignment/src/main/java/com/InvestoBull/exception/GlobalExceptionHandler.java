package com.InvestoBull.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CandleException.class)
	public ResponseEntity<MyErrorDetails> BookingsExceptionHandler(CandleException cx, WebRequest req) {
		
		MyErrorDetails err = new MyErrorDetails();
		err.setMessage(cx.getMessage());
		err.setDetails(req.getDescription(false));
		err.setTimestamp(LocalDateTime.now());
		ResponseEntity<MyErrorDetails> rs = new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		return rs;
	}
	
	@ExceptionHandler(IntervalException.class)
	public ResponseEntity<MyErrorDetails> RouteExceptionHandler(IntervalException ix, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails();
		err.setMessage(ix.getMessage());
		err.setDetails(req.getDescription(false));
		err.setTimestamp(LocalDateTime.now());
		ResponseEntity<MyErrorDetails> rs = new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		return rs;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> ParentExceptionHandler(Exception ex, WebRequest req) {
		
		MyErrorDetails err = new MyErrorDetails();
		err.setMessage(ex.getMessage());
		err.setDetails(req.getDescription(false));
		err.setTimestamp(LocalDateTime.now());
		ResponseEntity<MyErrorDetails> rs = new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		return rs;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> NohandlerFoundExceptionHandler(NoHandlerFoundException nf, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails();
		err.setMessage(nf.getMessage());
		err.setDetails(req.getDescription(false));
		err.setTimestamp(LocalDateTime.now());
		ResponseEntity<MyErrorDetails> rs = new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

		return rs;

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> MANVExceptionHandler(MethodArgumentNotValidException mv) {

		MyErrorDetails err = new MyErrorDetails();
		err.setMessage("Validation Error");
		err.setDetails(mv.getBindingResult().getFieldError().getDefaultMessage());
		err.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}
	
	
	
	

}
