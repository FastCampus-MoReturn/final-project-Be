package com.fastcampus.finalprojectbe.publicapi.dto;

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
@Schema(description = "주간아파트 가격 동향 지수 출력 DATA")
public class TradingPriceIndexResDTO {

    @Schema(description = "지수" ,example = "93.2745535484623")
    private String INDICES;
    @Schema(description = "지역구분 레벨", example = "0")
    private String LEVEL_NO;
    @Schema(description = "지역코드", example = "A2000")
    private String REGION_CD;
    @Schema(description = "지역명", example = "수도권")
    private String REGION_NM;
    @Schema(description = "조사 월", example = "20230227")
    private String RESEARCH_DATE;
    @Schema(description = "계약 타입(전세/매매)", example = "S")
    private String TR_GBN;


}
