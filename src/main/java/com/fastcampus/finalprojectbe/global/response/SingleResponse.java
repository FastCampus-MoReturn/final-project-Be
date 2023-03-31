package com.fastcampus.finalprojectbe.global.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "단일 Response")
public class SingleResponse<T> extends CommonResponse{

    @Schema(description = "Single Response Data")
    private T data;

}
