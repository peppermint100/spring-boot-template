package com.peppermint100.templateservice.service;

import com.peppermint100.templateservice.dto.TemplateDto;
import com.peppermint100.templateservice.entity.Template;
import com.peppermint100.templateservice.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateRepository templateRepository;

    public TemplateDto addTemplate(String name) {
        Template template = Template.of(name);
        Template createdTemplate = templateRepository.save(template);
        return TemplateDto.of(createdTemplate);
    }
}
