package com.mgvozdev.casino.controller.handler;

import com.mgvozdev.casino.exception.ChipException;
import com.mgvozdev.casino.exception.ErrorMessage;
import com.mgvozdev.casino.exception.PlayerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PlayerException.class)
    public ResponseEntity<String> handlePlayerException(Exception exception) {
        if (exception.getMessage().equals(ErrorMessage.NOT_FOUND)) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(ChipException.class)
    public ResponseEntity<String> handleChipException(Exception exception) {
        if (exception.getMessage().equals(ErrorMessage.NOT_FOUND)) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
