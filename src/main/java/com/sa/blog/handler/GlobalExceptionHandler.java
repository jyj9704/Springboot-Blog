package com.sa.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	//해당 사용자가 없습니다.
	@ExceptionHandler(value=Exception.class)
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
}
