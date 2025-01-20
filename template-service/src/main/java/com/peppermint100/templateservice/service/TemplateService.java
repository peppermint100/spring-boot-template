package com.peppermint100.templateservice.service;

import com.peppermint100.templateservice.dto.TemplateDto;
import com.peppermint100.templateservice.entity.Template;
import com.peppermint100.templateservice.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateQueryService templateQueryService;
    private final TemplateRepository templateRepository;

    public TemplateDto addTemplate(String name) {
        Template template = Template.of(name);
        Template createdTemplate = templateRepository.save(template);
        return TemplateDto.of(createdTemplate);
    }

    @Transactional
    public TemplateDto updateTemplate(UUID id, String name) {
        Template template = templateQueryService.getTemplateById(id);
        template.updateName(name);
        Template updatedTemplate = templateRepository.saveAndFlush(template); // updatedAt 값 반영을 위한 saveAndFlush
        return TemplateDto.of(updatedTemplate);
    }

    @Transactional
    public void deleteTemplate(UUID id) {
        Template template = templateQueryService.getTemplateById(id);
        templateRepository.delete(template);
    }
}
