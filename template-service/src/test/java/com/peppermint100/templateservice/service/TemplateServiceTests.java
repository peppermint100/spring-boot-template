package com.peppermint100.templateservice.service;

import com.peppermint100.templateservice.dto.TemplateDto;
import com.peppermint100.templateservice.entity.Template;
import com.peppermint100.templateservice.repository.TemplateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TemplateServiceTests {

    @InjectMocks private TemplateService templateService;
    @Mock private TemplateQueryService templateQueryService;
    @Mock private TemplateRepository templateRepository;

    @Test
    void 템플릿_수정_시_이름이_바뀐다() {
        UUID dummyUserId = UUID.randomUUID();
        UUID dummyTemplateId = UUID.randomUUID();
        String oldTemplateName = "oldTemplateName";
        String newTemplateName = "newTemplateName";
        Template oldTemplate = Template.of(dummyUserId, oldTemplateName);
        Template newTemplate = Template.of(dummyUserId, newTemplateName);

        // given
        BDDMockito.given(templateQueryService.getTemplateById(dummyTemplateId))
                .willReturn(oldTemplate);
        BDDMockito.given(templateRepository.saveAndFlush(oldTemplate)).willReturn(newTemplate);

        // when
        TemplateDto updatedTemplate = templateService.updateTemplate(dummyTemplateId, oldTemplateName);

        // then
        // 템플릿 이름이 업데이트 된다.
        Assertions.assertEquals(updatedTemplate.getName(), newTemplateName);
        // updateTemplate 내부의 비즈니스 로직이 실행된다.
        Mockito.verify(templateQueryService).getTemplateById(dummyTemplateId);
        Mockito.verify(templateRepository).saveAndFlush(oldTemplate);
    }
}
