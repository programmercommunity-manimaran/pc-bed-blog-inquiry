package com.programmercommunity.hicks.blog.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.programmercommunity.hicks.blog.exception.NotFound;
import com.programmercommunity.hicks.blog.model.Response;

@ControllerAdvice
public class BlogAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFound.class)
	public ResponseEntity<Response> handleUserNotFoundException(NotFound ex, WebRequest request) {
		return new ResponseEntity<Response>(new Response(404, "Not found"), HttpStatus.NOT_FOUND);
	}

}
