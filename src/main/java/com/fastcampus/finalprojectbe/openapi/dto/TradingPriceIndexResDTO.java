package com.fastcampus.finalprojectbe.openapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@ApiModel(value = "주간아파트 가격 동향 지수 출력 DATA")
public class TradingPriceIndexResDTO {

    @ApiModelProperty(value = "지수" ,example = "93.2745535484623")
    private String indices;
    @ApiModelProperty(value = "지역명", example = "수도권")
    private String region_nm;
    @ApiModelProperty(value = "조사 월", example = "20230227")
    private String research_date;
    @ApiModelProperty(value = "계약 타입(전세/매매)", example = "S")
    private String tr_gbn;


}
