package com.peppermint100.userservice.vo;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class GlobalErrorResponse {
    private String message;
    private Instant timestamp;
}
