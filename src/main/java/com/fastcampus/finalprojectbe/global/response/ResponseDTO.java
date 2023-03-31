package com.fastcampus.finalprojectbe.global.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "기본 반환값")
public class ResponseDTO<T> {


    @Schema(description = "HttpCode", example = "200")
    private Integer code;
    @Schema(description = "메세지", example = "요청에 성공하였습니다.")
    private String message;
    @Schema(description = "반환 데이터", example = "data")
    private T data;

    public ResponseDTO(T data) {
        this.code = HttpStatus.OK.value();
        this.message = "요청에 성공하였습니다.";
        this.data = data;
    }

    public static <T> ResponseDTO<T> empty() {
        return new ResponseDTO<>(null);
    }

}