package com.fastcampus.finalprojectbe.global.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "리스트 Response")
public class ListResponse<T> extends CommonResponse{

    @Schema(description = "Response Data List")
    private List<T> list;

}