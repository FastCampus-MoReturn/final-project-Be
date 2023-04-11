package com.fastcampus.finalprojectbe.openapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "아파트 실거래 상세자료 조회 입력 DATA")
public class TradingDetailReqDTO {

    @Schema(description = "등기부등본 address 값", example = "경기도 하남시 학암동 662 위례신도시엠코타운센트로엘 제6111동 제3층 제301호")
    private String address;
    @Schema(description = "최근 X개월 3개월: 3, 12개월: 12", example = "3")
    private int researchDate;
}
