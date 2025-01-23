package com.peppermint100.templateservice.vo;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateTemplateRequest {
    private UUID userId;
    private String name;
}
