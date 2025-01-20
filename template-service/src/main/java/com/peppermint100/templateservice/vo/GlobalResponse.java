package com.peppermint100.templateservice.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GlobalResponse<T> {
    private String message;
    private T data;
}
