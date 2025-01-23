package com.peppermint100.templateservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class TemplateDoesNotExistsException extends RuntimeException {
    private UUID templateId;
}
