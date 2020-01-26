package no.comp.finalproject.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ControllerAdvisor {

	@ExceptionHandler(NoHandlerFoundException.class)
	public String handleNoHandlerFoundException(Exception ex) {
		return "error404";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeEcxeption(Exception ex) {
		return "error500";
	}
	
}
