package com.musalasoft.gateways.exception;

import com.musalasoft.gateways.util.Constants;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
//        response.put(Constants.MESSAGE, e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(response, e.getStatus());
    }

}
