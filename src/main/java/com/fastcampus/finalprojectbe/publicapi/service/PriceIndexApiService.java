package com.fastcampus.finalprojectbe.publicapi.service;

import com.fastcampus.finalprojectbe.global.response.CommonResponse;

public interface PriceIndexApiService {

    CommonResponse tradingPriceIndex(String regionCode,String contractType,String researchDate);
}
