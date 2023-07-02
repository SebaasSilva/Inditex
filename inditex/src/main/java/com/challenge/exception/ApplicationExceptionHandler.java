package com.challenge.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

  /**
   * This method map the exception to handle it.
   *
   * @param exception Object with the exception information.
   * @return errorMap Map with the necessary information to let the user know why is showing up the exception.
   */
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(PriceNotFoundException.class)
  public Map<String, String> handlerBusinessException(PriceNotFoundException exception) {
    Map<String, String> errorMap = new HashMap<>();
    errorMap.put("Error: ", exception.getMessage());
    return errorMap;
  }
}