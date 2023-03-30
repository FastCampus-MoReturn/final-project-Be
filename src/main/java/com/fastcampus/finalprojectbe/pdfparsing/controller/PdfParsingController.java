package com.fastcampus.finalprojectbe.pdfparsing.controller;


import com.fastcampus.finalprojectbe.global.response.ResponseDTO;
import com.fastcampus.finalprojectbe.pdfparsing.service.PdfParsingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@Api(tags = "PDF 파싱 서비스")
public class PdfParsingController {

    private final PdfParsingService pdfParsingService;

    @Operation(summary = "PDF 파싱", description = "encType=\"multipart/form-data input type=\"file\" 업로드 ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "PDF 파일이 아님"),
            @ApiResponse(responseCode = "404", description = "잘못된 접근"),
            @ApiResponse(responseCode = "500", description = "파싱 실패 서버에러")
    })
    @PostMapping("/api/pdfupload")
    @ResponseBody
    public ResponseDTO<?> pdfParsing(
            @ApiParam(value = "PDF 파일을 업로드해 주세요.",required = true)
            @RequestPart("multipartFile")
            @RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile) throws IOException {
        return pdfParsingService.pdfParsing(multipartFile);

    }
}

