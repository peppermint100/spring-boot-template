package com.peppermint100.templateservice.vo;

import com.peppermint100.templateservice.dto.TemplateDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TemplateVO {
    private UUID id;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;

    public static TemplateVO of(TemplateDto dto) {
        return new TemplateVO(dto.getId(), dto.getName(), dto.getCreatedAt(), dto.getUpdatedAt());
    }
}
