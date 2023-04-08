package com.fastcampus.finalprojectbe.terms.controller;

import com.fastcampus.finalprojectbe.terms.dto.TermsResDTO;
import com.fastcampus.finalprojectbe.terms.service.TermsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Api(tags = "등기 용어 사전")
public class TermsController {
    private final TermsService termsService;

    @PostMapping("api/terms")
    @ApiOperation(value = "등기 용어 사전", notes = "등기 용어 사전 검색")
    public ResponseEntity<Map<String, Object>> searchTerms(
            @RequestParam(name = "keyword") String keyword, Pageable pageable) {
        Page<TermsResDTO> page = termsService.searchTerms(keyword, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("terms", page.getContent());
        response.put("lastpage", page.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping("api/terms/{keyword}")
    public ResponseEntity<Map<String, Object>> getTermsListByKeyword(
            @PathVariable("keyword")
            @ApiParam(value = "가", required = true) String keyword, Pageable pageable) {
        Page<TermsResDTO> page = termsService.getTermsListByKeyword(keyword, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("terms", page.getContent());
        response.put("lastpage", page.getTotalPages());

        return ResponseEntity.ok(response);
    }

}