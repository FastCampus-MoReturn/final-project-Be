package com.fastcampus.finalprojectbe.global.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "공통 Response")
public class CommonResponse {

    @Schema(description = "Response 성공 여부")
    private boolean isSuccess;

    @Schema(description ="Status 코드")
    private int code;

    @Schema(description = "Response Message")
    private String message;

    public CommonResponse(){}
    public CommonResponse(int code,String msg){
        this.code = code;
        this.message = msg;
    };


}
