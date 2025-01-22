package com.peppermint100.userservice.exception;

import com.peppermint100.userservice.vo.GlobalErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FailToSignUpException.class)
    public GlobalErrorResponse handleFailToSignUpException(FailToSignUpException ex) {
        String message = ex.getMessage();
        return GlobalErrorResponse.builder().message(message).timestamp(Instant.now()).build();
    }

    @ExceptionHandler(FailToLoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GlobalErrorResponse handleFailToLoginException(FailToLoginException ex) {
        String message = ex.getMessage();
        return GlobalErrorResponse.builder().message(message).timestamp(Instant.now()).build();
    }
}
