package com.peppermint100.templateservice.controller;

import com.peppermint100.templateservice.dto.TemplateDto;
import com.peppermint100.templateservice.service.TemplateService;
import com.peppermint100.templateservice.vo.CreateTemplateRequest;
import com.peppermint100.templateservice.vo.TemplateVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/templates")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping
    public ResponseEntity<TemplateVO> addTemplate(@RequestBody  CreateTemplateRequest request) {
        String name = request.getName();
        TemplateDto templateDto = templateService.addTemplate(name);
        TemplateVO templateVO = TemplateVO.of(templateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(templateVO);
    }
}
