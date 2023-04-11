package com.fastcampus.finalprojectbe.global.exception;

import com.fastcampus.finalprojectbe.global.response.CommonResponse;
import com.fastcampus.finalprojectbe.global.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse defaultException(HttpServletRequest request, Exception e) {
        return responseService.getFailResponse(500,"서버 에러");
    }

    @ExceptionHandler(PDFValidationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse validationException(HttpServletRequest req, PDFValidationException e){
        return responseService.getFailResponse(403,"PDF 요약본이 없습니다.");
    }

    @ExceptionHandler(NoSearchAdressException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse noSearchAdressException (HttpServletRequest req, NoSearchAdressException e){
        return responseService.getFailResponse(400,"주소 입력이 잘못되었습니다.");
    }

    @ExceptionHandler(KeywordValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResponse keywordValidationException(HttpServletRequest request, KeywordValidationException e) {
        return responseService.getFailResponse(400, "잘못된 키워드를 입력했습니다.");
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected CommonResponse dataNotFoundException(HttpServletRequest request, DataNotFoundException e) {
        return responseService.getFailResponse(404, "해당 키워드의 결과값이 없습니다.");
    }

}