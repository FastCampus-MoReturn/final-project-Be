package com.fastcampus.finalprojectbe.openapi.service.impl;

import com.fastcampus.finalprojectbe.global.exception.NoSearchAdressException;
import com.fastcampus.finalprojectbe.global.response.CommonResponse;
import com.fastcampus.finalprojectbe.global.response.ResponseService;
import com.fastcampus.finalprojectbe.global.xml.XmlReader;
import com.fastcampus.finalprojectbe.openapi.dto.TradingDetailReqDTO;
import com.fastcampus.finalprojectbe.openapi.dto.TradingDetailResDTO;
import com.fastcampus.finalprojectbe.openapi.dto.TradingPriceIndexResDTO;
import com.fastcampus.finalprojectbe.openapi.service.AddressService;
import com.fastcampus.finalprojectbe.openapi.service.OpenApiService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenApiServiceImpl implements OpenApiService {

    private final ResponseService responseService;
    private final AddressService addressService;
    private final XmlReader xmlReader;

    @Value("${api.servicekey}")
    private String SERVICE_KEY;

    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
    String formattedDate = currentDate.format(formatter);

    @Override
    public CommonResponse tradingPriceIndex(String regionCode, String contractType, int researchDate) {

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.odcloud.kr")
                .path("/api/WeeklyAptTrendSvc/v1/getAptTradingPriceIndex")
                .queryParam("page", 1)
                .queryParam("perPage", 50)
                .queryParam("returnType", "json")
                .queryParam("cond[REGION_CD::EQ]", regionCode)
                .queryParam("cond[RESEARCH_DATE::GTE]", createResearchDate(researchDate))
                .queryParam("cond[RESEARCH_DATE::LTE]", formattedDate)
                .queryParam("cond[TR_GBN::EQ]", contractType)
                .encode();


        URI uri = URI.create(builder.build().toUriString() + ("&serviceKey=" + SERVICE_KEY));
//        log.info("uri : {}", uri);
        return responseService.getListResponse(researchDateSortReturn(uri));
    }

    @Override
    public CommonResponse tradingDetail(TradingDetailReqDTO tradingDetailReqDTO) {
        String jibun = "";
        String aptName = "";
        String address = tradingDetailReqDTO.getAddress();
        int researchDate = tradingDetailReqDTO.getResearchDate();
        try {
            jibun = addressService.findJibun(address);
            aptName = addressService.findAptName(address);
            tradingDetailGetApi(address, researchDate);
        }catch (Exception e) {
            throw new NoSearchAdressException();
        }
        List<TradingDetailResDTO> aptList = xmlReader.getAptList();
        List<TradingDetailResDTO> result = new ArrayList<>();
        for (TradingDetailResDTO apt : aptList) {
            if (apt.getJibun().equals(jibun) && apt.getTradeAptName().contains(aptName)) {
                result.add(apt);
            }
        }
        return responseService.getListResponse(result);
    }

    public void tradingDetailGetApi(String address, int researchDate) throws ParserConfigurationException, IOException, SAXException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        String siGunGuCode = addressService.searchsiGunGuCodeCode(address);

        for (int i = 0; i < researchDate; i++) {

            LocalDate date = currentDate.minusMonths(i);
            String period = date.format(formatter);

            UriComponentsBuilder uribuilder = UriComponentsBuilder.newInstance()
                    .scheme("http")
                    .host("openapi.molit.go.kr")
                    .path("/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev")
                    .queryParam("serviceKey", SERVICE_KEY)
//                .queryParam("pageNo", 1)
                    .queryParam("numOfRows", "500")
                    .queryParam("LAWD_CD", siGunGuCode)
                    .queryParam("DEAL_YMD", period);

            URI uri = URI.create(uribuilder.build().toUriString());
//            log.info("uri : {}", uri);

            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(uri.toString());
            xmlReader.parser(doc);
        }

    }

    public List<TradingPriceIndexResDTO> researchDateSortReturn(URI uri) {

        WebClient webClient = WebClient.create();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonNode -> {
                    List<JsonNode> nodeList = jsonNode.findValues("data");
                    List<JsonNode> sortedNodeList = nodeList.stream()
                            .flatMap(node -> StreamSupport.stream(node.spliterator(), false))
                            .sorted(Comparator.comparingInt(node -> node.get("RESEARCH_DATE").asInt()))
                            .collect(Collectors.toList());
                    List<TradingPriceIndexResDTO> dtoList = new ArrayList<>();
                    for (JsonNode node : sortedNodeList) {
                        TradingPriceIndexResDTO dto = new TradingPriceIndexResDTO();
                        dto.setResearch_date(node.get("RESEARCH_DATE").asText());
                        dto.setIndices(node.get("INDICES").asText());
                        dto.setRegion_nm(node.get("REGION_NM").asText());
                        dto.setTr_gbn(node.get("TR_GBN").asText());
                        dtoList.add(dto);
                    }
                    return dtoList;
                })
                .block();


    }

    public String createResearchDate(int researchDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        LocalDate date = currentDate.minusMonths(researchDate);
        return date.format(formatter);
    }

}
