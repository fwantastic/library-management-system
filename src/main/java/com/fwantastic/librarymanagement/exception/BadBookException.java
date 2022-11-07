package com.fwantastic.librarymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadBookException extends RuntimeException {

  public BadBookException(String message) {
    super(message);
  }
}
