package com.fastcampus.finalprojectbe.openapi.service;

import com.fastcampus.finalprojectbe.global.response.CommonResponse;
import com.fastcampus.finalprojectbe.openapi.dto.TradingDetailReqDTO;

public interface OpenApiService {

    CommonResponse tradingPriceIndex(String regionCode,String contractType,int researchDate);

    CommonResponse tradingDetail(TradingDetailReqDTO tradingDetailReqDTO);
}
