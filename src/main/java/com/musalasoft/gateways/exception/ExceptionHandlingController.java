package com.musalasoft.gateways.exception;

import com.musalasoft.gateways.util.Constants;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlingController {

    @ExceptionHandler(ResponseStatusException.class)
    private ResponseEntity<Object> handleResponseStatusException(ResponseStatusException e) {
        Map<String, Object> response = new HashMap<>();
        response.put(Constants.ERROR, e.getReason());
        log.error(e.getMessage());
        return new ResponseEntity<>(response, e.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Object> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        Map<String, Object> response = new HashMap<>();
        response.put(Constants.ERROR, e.getFieldError().getField() + Constants.INVALID_VALUE);
        log.error(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
