package com.fastcampus.finalprojectbe.openapi.dto;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class tradingDetailResDTO {

    private String serialNumber; // 일련번호
    private String amount;//거래금액
    private String tradeType;//거래유형
    private String buildYear;//건축년도
    private String tradeYear; //계약 연
    private String tradeMonth; //계약 월
    private String tradeDay; //계약 일
    private String siGunguCode; //법정동시군구코드
    private String eupMyunDongCode; //법정동읍면동코드
    private String legDong; //법정동
    private String tradeAptName; //거래된 아파트 이름
    private String jibun; //주소 지번
    private String tradeExclusiveArea; //거래된전용면적
    private String tradefloor; //거래된층


}
