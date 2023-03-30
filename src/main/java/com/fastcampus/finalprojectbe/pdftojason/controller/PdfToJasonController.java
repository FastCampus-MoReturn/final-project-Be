package com.fastcampus.finalprojectbe.pdftojason.controller;


import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class PdfToJasonController {


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public void jsonParsing(@RequestParam(value = "file", required = false) MultipartFile multipartFile, HttpServletResponse response) throws IOException {

        PDFParser pdfParser = new PDFParser(multipartFile.getInputStream());
        pdfParser.parse();
        PDDocument document = pdfParser.getPDDocument();

        PDFTextStripper pdfStripper = new PDFTextStripper();
        String pdfText = pdfStripper.getText(document);
        JSONObject json = new JSONObject();
        JSONArray lines = new JSONArray();

        String[] textLines = pdfText.split("\\r?\\n");

        for (int i = 0; i < textLines.length; i++) {
            JSONObject lineObject = new JSONObject();
            lineObject.put(String.valueOf(i + 1), textLines[i].trim());
            lines.put(lineObject);
        }

        json.put("lines", lines);
        document.close();

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().print(json);
    }


}

