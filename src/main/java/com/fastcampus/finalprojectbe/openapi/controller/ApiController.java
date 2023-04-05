package com.fastcampus.finalprojectbe.openapi.controller;


import com.fastcampus.finalprojectbe.global.response.CommonResponse;
import com.fastcampus.finalprojectbe.openapi.service.OpenApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = "외부 API 서비스")
public class ApiController {

    private final OpenApiService openApiService;

    @Operation(summary = "주간아파트 가격 동향 지수", description = "3가지 정보를 입력받아 데이터를 출력한다. 오늘날짜 기준 내림차순으로 데이터를 제공, 값이 여러개일경우 여러개 제공")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "인증정보 오류"),
            @ApiResponse(responseCode = "404", description = "잘못된 접근"),
            @ApiResponse(responseCode = "500", description = "API 서버에러")
    })
    @GetMapping("/api/tradingPriceIndex/{regionCode}/{contractType}/{researchDate}")  //주간아파트 가격 동향 지수
    public CommonResponse tradingPriceIndex(
            @PathVariable("regionCode")
            @ApiParam(value = "수도권 A2000, 서울 11000", required = true) String regionCode, // (수도권 A2000, 서울 11000)
            @PathVariable("contractType")
            @ApiParam(value = "매매:S , 전세:D", required = true) String contractType,// (매매:S , 전세:D)
            @PathVariable("researchDate")
            @ApiParam(value = "1년:12, 3개월:3, 6개월:6", required = true) int researchDate // (1년:12, 3개월:3, 6개월:6)
    ) {

        return openApiService.tradingPriceIndex(regionCode,contractType,researchDate);
    }


    @Operation(summary = "아파트 실거래 상세 자료 조회", description = "address 값과, 현재 날짜 기준 조회 개월수 를 입력")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "주소 입력이 잘못되었습니다."),
            @ApiResponse(responseCode = "404", description = "잘못된 접근"),
            @ApiResponse(responseCode = "500", description = "API 서버에러")
    })
    @GetMapping("/api/tradingdetail/{address}/{researchDate}/")  //아파트 실거래 상세 자료 조회
    public CommonResponse tradingDetail(
            @PathVariable("address")
            @ApiParam(value = "등기부등본 parsing 데이터 중 address 데이터", required = true) String address,
            @PathVariable("researchDate")
            @ApiParam(value = "1년:12, 3개월:3, 6개월:6", required = true) int researchDate // (1년:12, 3개월:3, 6개월:6)
    ) throws ParserConfigurationException, IOException {
        return openApiService.tradingDetail(address,researchDate);
    }






}
