package com.peppermint100.templateservice.controller;

import com.peppermint100.templateservice.dto.TemplateDto;
import com.peppermint100.templateservice.service.TemplateQueryService;
import com.peppermint100.templateservice.service.TemplateService;
import com.peppermint100.templateservice.vo.CreateTemplateRequest;
import com.peppermint100.templateservice.vo.GlobalResponse;
import com.peppermint100.templateservice.vo.TemplateVO;
import com.peppermint100.templateservice.vo.UpdateTemplateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/templates")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @GetMapping
    public ResponseEntity<GlobalResponse> getTemplatesByUserId(@RequestParam("userId") UUID userId) {
        List<TemplateDto> templateDtoList = templateService.getTemplatesByUserId(userId);

        List<TemplateVO> templateVOs = templateDtoList.stream().map(TemplateVO::of).collect(Collectors.toList());
        GlobalResponse<List<TemplateVO>> response = GlobalResponse.<List<TemplateVO>>builder()
                .message("템플릿 목록을 가져왔습니다.")
                .data(templateVOs)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse> addTemplate(@RequestBody  CreateTemplateRequest request) {
        UUID userId = request.getUserId();
        String name = request.getName();
        TemplateDto templateDto = templateService.addTemplate(userId, name);
        TemplateVO templateVO = TemplateVO.of(templateDto);
        GlobalResponse<TemplateVO> response = GlobalResponse.<TemplateVO>builder().message("템플릿을 추가했습니다.").data(templateVO).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{templateId}")
    public ResponseEntity<GlobalResponse> updateTemplate(
            @PathVariable("templateId") UUID templateId,
            @RequestBody UpdateTemplateRequest request
    ) {
        String name = request.getName();
        TemplateDto templateDto = templateService.updateTemplate(templateId, name);
        TemplateVO templateVO = TemplateVO.of(templateDto);
        GlobalResponse<TemplateVO> response = GlobalResponse.<TemplateVO>builder().message("템플릿을 변경했습니다.").data(templateVO).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{templateId}")
    public ResponseEntity<GlobalResponse> deleteTemplate(@PathVariable("templateId") UUID templateId) {
        templateService.deleteTemplate(templateId);
        GlobalResponse<Object> response = GlobalResponse.builder().message("템플릿을 삭제했습니다.").build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
