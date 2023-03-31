package com.fastcampus.finalprojectbe.pdfparsing.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "PDF파싱 출력 DATA")
public class PdfParsingResDTO {

    @Schema(description = "등기부 등본 고유번호")
    private String uniqueNumber; // 고유번호
    @Schema(description = "최고층수")
    private String maxFloor; //최고층수
    @Schema(description = "주소")
    private String address; // 주소
    @Schema(description = "소유자 리스트")
    private Map<Integer, HashMap<String, String>> owner; // 소유자
    @Schema(description = "전용면적")
    private String exclusiveArea; //전용면적
    @Schema(description = "전세 보증금의 합")
    private Long sumJeonse_deposit; // 보증금
    @Schema(description = "전세권자 리스트")
    private Map<Integer, String> jeonseAuthorityList; // 전세권자 리스트
    @Schema(description = "근저당 건수")
    private int mortgageCount; // 근저당 건수
    @Schema(description = "근저당권자 리스트")
    private Map<Integer, String> mortgageeList; // 근저당권자 리스트
    @Schema(description = "근저당 채권최고액의 합")
    private Long sumMax_mortgageBond; // 채권최고액의 합
    @Schema(description = "질권설정 건수")
    private int pledgeCount; // 질권설정 건수
    @Schema(description = "질권 설정 채권자 리스트")
    private Map<Integer, String> pledgeCreditorList; // 질권 채권자 리스트
    @Schema(description = "질권설정 채권액의 합")
    private Long sumPledge; // 질권설정 채권액의 합
    @Schema(description = "압류 건수")
    private int attachmentCount; // 압류건수
    @Schema(description = "가압류 청구금액의 합")
    private Long sumAncillary_Attachment; // 가압류 청구금액의 합
    @Schema(description = "압류 채권자 리스트")
    private Map<Integer, String> attachmentList; // 압류 채권자 리스트
    @Schema(description = "열람일시")
    private String printingDate; // 열람일시

}
