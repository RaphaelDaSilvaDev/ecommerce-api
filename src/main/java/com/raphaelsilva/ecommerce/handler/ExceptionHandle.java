package com.raphaelsilva.ecommerce.handler;

import java.util.HashMap;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandle {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ExceptionDTO handleNotFound(NotFoundException exception, HttpServletRequest request){
    return new ExceptionDTO(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), exception.getMessage(), request.getServletPath());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionDTO handleValidationError(MethodArgumentNotValidException exception, HttpServletRequest request){
    HashMap<String, String> errorMessage = new HashMap<>();
    exception.getBindingResult().getFieldErrors().forEach(err -> errorMessage.put(err.getField(), err.getDefaultMessage()));
    return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), errorMessage.toString(), request.getServletPath());
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionDTO handleDataIntegrationValidationError(DataIntegrityViolationException exception, HttpServletRequest request){
    return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), exception.getMostSpecificCause().getMessage(), request.getServletPath());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ExceptionDTO handleServerError(Exception exception, HttpServletRequest request){
    return new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), exception.toString(), request.getServletPath());
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ExceptionDTO handleEmptyError(EmptyResultDataAccessException exception, HttpServletRequest request){
    return new ExceptionDTO(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), "Not found!", request.getServletPath());
  }
}
