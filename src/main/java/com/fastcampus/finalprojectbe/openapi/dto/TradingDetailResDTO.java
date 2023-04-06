package com.fastcampus.finalprojectbe.openapi.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "아파트 실거래 상세자료 조회 출력 DATA")
public class TradingDetailResDTO {

    @Schema(description = "일련번호", example = "41450-324")
    private String serialNumber; // 일련번호
    @Schema(description = "거래금액(만원)", example = "146000")
    private String amount;//거래금액
    @Schema(description = "거래유형", example = "중개거래")
    private String tradeType;//거래유형
    @Schema(description = "건축년도", example = "2016")
    private String buildYear;//건축년도
    @Schema(description = "계약 연", example = "2023")
    private String tradeYear; //계약 연
    @Schema(description = "계약 월", example = "3")
    private String tradeMonth; //계약 월
    @Schema(description = "계약 일", example = "8")
    private String tradeDay; //계약 일
    @Schema(description = "법정동시군구코드", example = "41450")
    private String siGunguCode; //법정동시군구코드
    @Schema(description = "법정동읍면동코드", example = "11600")
    private String eupMyunDongCode; //법정동읍면동코드
    @Schema(description = "법정동", example = "학암동")
    private String legDong; //법정동
    @Schema(description = "거래된 아파트 이름", example = "위례신도시엠코타운센트로엘")
    private String tradeAptName; //거래된 아파트 이름
    @Schema(description = "주소 지번", example = "662")
    private String jibun; //주소 지번
    @Schema(description = "거래된전용면적", example = "98.7995")
    private String tradeExclusiveArea; //거래된전용면적
    @Schema(description = "거래된층", example = "11")
    private String tradefloor; //거래된층


}
