package com.fastcampus.finalprojectbe.pdfparsing.service;

import com.fastcampus.finalprojectbe.global.response.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PdfParsingService {

    CommonResponse pdfParsing(MultipartFile multipartFile) throws IOException;
}
