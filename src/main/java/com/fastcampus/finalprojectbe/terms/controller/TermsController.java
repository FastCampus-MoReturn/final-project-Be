package com.fastcampus.finalprojectbe.terms.controller;

import com.fastcampus.finalprojectbe.terms.dto.TermsResDTO;
import com.fastcampus.finalprojectbe.terms.service.TermsService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "등기 용어 사전")
public class TermsController {

    private final TermsService service;

    @GetMapping("api/terms")
    @Operation(summary = "등기 용어 사전 목록", description = "등기 용어 사전 목록 전체 불러오기")
    @ApiResponse(responseCode = "200", description = "등기 용어 사전 목록 조회 성공")

    public List<TermsResDTO> selectTermsList() {
        return service.selectTermsList();
    }
}
