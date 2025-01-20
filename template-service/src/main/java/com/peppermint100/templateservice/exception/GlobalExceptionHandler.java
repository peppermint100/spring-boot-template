package com.peppermint100.templateservice.exception;

import com.peppermint100.templateservice.vo.GlobalErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TemplateDoesNotExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GlobalErrorResponse handleTemplateDoesNotExistException(TemplateDoesNotExistsException ex) {
        String message = "존재하지 않는 템플릿입니다.";
        log.error("id {} 템플릿은 존재하지 않습니다.", ex.getTemplateId());
        return new GlobalErrorResponse(message, Instant.now());
    }

}
