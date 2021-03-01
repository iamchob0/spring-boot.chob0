package com.iam.chobo.domain.board;

import com.iam.chobo.domain.board.exception.NotAllowException;
import com.iam.chobo.global.error.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@ControllerAdvice
public class BoardExceptionHandler {

    @ExceptionHandler(NotAllowException.class)
    public ResponseEntity<ErrorResponse>  NotAllowExceptionException(NotAllowException e) {
        log.error("NotAllowExceptionException {}", e);
        final ErrorResponse response = ErrorResponse.of(e.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

}