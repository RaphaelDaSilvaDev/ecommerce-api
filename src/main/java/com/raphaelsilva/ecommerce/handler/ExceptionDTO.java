package com.raphaelsilva.ecommerce.handler;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExceptionDTO {
  LocalDateTime timestamp = LocalDateTime.now();
  Number status;
  String error;
  String message;
  String path;
  
  public ExceptionDTO(Number status, String error, String message, String path) {
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
  }
}
