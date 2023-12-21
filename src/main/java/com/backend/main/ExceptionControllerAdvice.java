package com.backend.main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e, Model model) {
		e.printStackTrace();
		log.debug("Exception: 내부 에러");
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
	}
}
