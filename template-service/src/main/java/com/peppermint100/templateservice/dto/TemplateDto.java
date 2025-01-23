package com.peppermint100.templateservice.dto;

import com.peppermint100.templateservice.entity.Template;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TemplateDto {
    private UUID id;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;

    public static TemplateDto of(Template template) {
        return new TemplateDto(template.getId(), template.getName(), template.getCreatedAt(), template.getUpdatedAt());
    }
}
