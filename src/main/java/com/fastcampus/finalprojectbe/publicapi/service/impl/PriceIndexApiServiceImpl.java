package com.fastcampus.finalprojectbe.publicapi.service.impl;

import com.fastcampus.finalprojectbe.global.response.CommonResponse;
import com.fastcampus.finalprojectbe.global.response.ResponseService;
import com.fastcampus.finalprojectbe.publicapi.dto.TradingPriceIndexResDTO;
import com.fastcampus.finalprojectbe.publicapi.service.PriceIndexApiService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

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
public class PriceIndexApiServiceImpl implements PriceIndexApiService {

    private final ResponseService responseService;

    @Value("${api.servicekey}")
    private String SERVICE_KEY;

    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
    String formattedDate = currentDate.format(formatter);

    @Override
    public CommonResponse tradingPriceIndex(String regionCode, String contractType, String researchDate) {

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
                        dto.setRESEARCH_DATE(node.get("RESEARCH_DATE").asText());
                        dto.setINDICES(node.get("INDICES").asText());
                        dto.setREGION_NM(node.get("REGION_NM").asText());
                        dto.setTR_GBN(node.get("TR_GBN").asText());
                        dtoList.add(dto);
                    }
                    return dtoList;
                })
                .block();


    }

    public String createResearchDate(String researchDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        LocalDate date = currentDate.minusMonths(Long.parseLong(researchDate));
        return date.format(formatter);
    }

}
