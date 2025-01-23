package com.peppermint100.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FailToLoginException extends RuntimeException {
    private String message;
}
