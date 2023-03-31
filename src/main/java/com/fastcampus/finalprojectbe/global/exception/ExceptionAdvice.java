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

}