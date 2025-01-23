package com.peppermint100.templateservice.service;

import com.peppermint100.templateservice.exception.TemplateDoesNotExistsException;
import com.peppermint100.templateservice.repository.TemplateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TemplateQueueServiceTests {

    @InjectMocks private TemplateQueryService templateQueryService;
    @Mock private TemplateRepository templateRepository;

    @Test
    void 존재하지_않는_템플릿_id_쿼리시_예외처리() {
        UUID invalidTemplateId = UUID.randomUUID();

        BDDMockito.given(templateRepository.findById(invalidTemplateId))
                .willReturn(Optional.empty());

        Assertions.assertThrows(TemplateDoesNotExistsException.class, () -> {
            templateQueryService.getTemplateById(invalidTemplateId);
        });
    }
}
