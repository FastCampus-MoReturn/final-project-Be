package com.fastcampus.finalprojectbe.openapi.service;

import com.fastcampus.finalprojectbe.global.response.CommonResponse;

public interface OpenApiService {

    CommonResponse tradingPriceIndex(String regionCode,String contractType,int researchDate);

    CommonResponse tradingDetail(String address, int researchDate);
}
