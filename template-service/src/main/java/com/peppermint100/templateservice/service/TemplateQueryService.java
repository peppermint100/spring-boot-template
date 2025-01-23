package com.peppermint100.templateservice.service;

import com.peppermint100.templateservice.entity.Template;
import com.peppermint100.templateservice.exception.TemplateDoesNotExistsException;
import com.peppermint100.templateservice.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TemplateQueryService {

    private final TemplateRepository templateRepository;

    @Transactional(readOnly = true)
    public List<Template> getTemplatesByUserId(UUID userId) {
        return templateRepository.findAllByUserId(userId);
    }

    @Transactional(readOnly = true)
    public Template getTemplateById(UUID id) {
        return templateRepository.findById(id).orElseThrow(() -> new TemplateDoesNotExistsException(id));
    }
}
