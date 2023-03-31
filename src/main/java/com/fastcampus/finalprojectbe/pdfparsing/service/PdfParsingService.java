package com.fastcampus.finalprojectbe.pdfparsing.service;

import com.fastcampus.finalprojectbe.global.response.ResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PdfParsingService {

    ResponseDTO<?> pdfParsing(MultipartFile multipartFile) throws IOException;
}
