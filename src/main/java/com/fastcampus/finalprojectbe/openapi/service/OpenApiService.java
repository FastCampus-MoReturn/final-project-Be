package com.fastcampus.finalprojectbe.openapi.service;

import com.fastcampus.finalprojectbe.global.response.CommonResponse;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface OpenApiService {

    CommonResponse tradingPriceIndex(String regionCode,String contractType,int researchDate);

    CommonResponse tradingDetail(String address, int researchDate) throws ParserConfigurationException, IOException, SAXException;
}
