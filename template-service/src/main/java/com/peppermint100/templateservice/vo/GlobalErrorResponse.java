package com.peppermint100.templateservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class GlobalErrorResponse {
    private String message;
    private Instant timestamp;
}
