package com.fastcampus.finalprojectbe.terms.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TermsResDTO {
    private String title; // 제목
    private String description; // 내용
}
