package com.fastcampus.finalprojectbe.pdfparsing.service.impl;

import com.fastcampus.finalprojectbe.global.response.ErrorResponseDTO;
import com.fastcampus.finalprojectbe.global.response.ResponseDTO;
import com.fastcampus.finalprojectbe.pdfparsing.dto.PdfParsingResDTO;
import com.fastcampus.finalprojectbe.pdfparsing.service.PdfParsingService;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class PdfParsingImpl implements PdfParsingService {
    @Override
    public ResponseDTO<?> pdfParsing(MultipartFile multipartFile) throws IOException {
        try {
            String fileName = multipartFile.getOriginalFilename();
            String fileExtension = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf(".") + 1);

            if (fileExtension.equals("pdf")) {
                PDFParser pdfParser = new PDFParser(multipartFile.getInputStream());
                pdfParser.parse();
                PDDocument document = pdfParser.getPDDocument();
                PDFTextStripper pdfStripper = new PDFTextStripper();
                String pdfText = pdfStripper.getText(document);
                document.close();

                PdfParsingResDTO pdfParsingResDTO = new PdfParsingResDTO();

                MaxFloorParsing(pdfText, pdfParsingResDTO);
                exclusiveAreaParsing(pdfText, pdfParsingResDTO);
                summaryParsing(pdfText, pdfParsingResDTO);
                return new ResponseDTO<>(pdfParsingResDTO);
            }
        } catch (NullPointerException e) {
            return new ErrorResponseDTO(404, "파일이 없거나 잘못된 접근입니다.").toResponse();
        }
        return new ErrorResponseDTO(400, "파일형식이 잘못되었습니다").toResponse();
    }

    public void MaxFloorParsing(String pdfText, PdfParsingResDTO pdfParsingResDTO) {

        String[] textList = pdfText.split("콘크리트", 2);
        String regex = "\\d+층";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(textList[1]);
        if (matcher.find()) {
            String match = matcher.group();
            pdfParsingResDTO.setMaxFloor(match);
        }
    }

    public void exclusiveAreaParsing(String pdfText, PdfParsingResDTO pdfParsingResDTO) {

        String[] textList = pdfText.split("( 전유부분의 건물의 표시 )", 2);
        String[] parsingLines = textList[textList.length - 1].split("( 대지권의 표시 )");

        String regex = "\\d+\\.\\d+㎡";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(parsingLines[0]);
        if (matcher.find()) {
            String match = matcher.group(0);
            pdfParsingResDTO.setExclusiveArea(match);
        }
    }


    public void summaryParsing(String pdfText, PdfParsingResDTO pdfParsingResDTO) {

        String[] textList = pdfText.split("주요 등기사항 요약", 2);
        String[] parsingLines = textList[textList.length - 1].split("1[.]|2[.]|3[.]", 4);
        numberAddressParsing(parsingLines[0], pdfParsingResDTO);
        ownerParsing(parsingLines[1], pdfParsingResDTO);
        attachmentParsing(parsingLines[2], pdfParsingResDTO);
        attachmentNameParsing(parsingLines[2], pdfParsingResDTO);
        jeonseMortgageParsing(parsingLines[3], pdfParsingResDTO);
        jeonseNameParsing(parsingLines[3], pdfParsingResDTO);
        mortgageNameParsing(parsingLines[3], pdfParsingResDTO);
        bondCreditorParsing(parsingLines[3], pdfParsingResDTO);
        printingDateParsing(parsingLines[3], pdfParsingResDTO);
    }


    public void numberAddressParsing(String parsingLine, PdfParsingResDTO pdfParsingResDTO) {

        String[] header = parsingLine.split("바랍니다.", 2);
        String[] parsingHeader = header[header.length - 1].split("\\[집합건물]|\\[건물]", 2);
        pdfParsingResDTO.setUniqueNumber(parsingHeader[0].substring(7).trim());
        pdfParsingResDTO.setAddress(parsingHeader[parsingHeader.length - 1].trim());

    }

    public void ownerParsing(String parsingLine, PdfParsingResDTO pdfParsingResDTO) {

        String regex = "(?<name>[가-힣]+) \\((소유자|공유자)\\) (?<age>\\d{6}-\\*{7}) (?<share>[0-9/분의|단독소유 ]+) (?<owneraddress>[^\\n]+).* (?<rank>\\d+)";
        String[] lineList = parsingLine.split("\r\n");

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(parsingLine);
        Map<Integer, HashMap<String, String>> ownerMap = new HashMap<>();
        int count = 1;
        while (matcher.find()) {
            HashMap<String, String> owner = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            owner.put("name", matcher.group("name"));
            owner.put("age", matcher.group("age"));
            owner.put("share", matcher.group("share"));
            sb.append(matcher.group("owneraddress")).append(" ");
            sb.append(lineList[lineList.length - 1]);
            owner.put("ownerAddress", sb.toString());
            owner.put("rank", matcher.group("rank"));

            ownerMap.put(count++, owner);
        }
        pdfParsingResDTO.setOwner(ownerMap);
    }

    public void attachmentParsing(String parsingLine, PdfParsingResDTO pdfParsingResDTO) {

        String regex = "(청구금액)\\s+금(\\d{1,3}(,\\d{3})*) 원";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(parsingLine);
        long sumAncillary_Attachment = 0;
        int attachmentCount = 0;

        while (matcher.find()) {
            String match = matcher.group();
            long value = Long.parseLong(match.replaceAll("[^0-9]", ""));
            if (match.startsWith("청구금액")) {
                sumAncillary_Attachment += value;
                attachmentCount++;
            }
        }
        pdfParsingResDTO.setAttachmentCount(attachmentCount);
        pdfParsingResDTO.setSumAncillary_Attachment(sumAncillary_Attachment);
    }


    public void jeonseMortgageParsing(String parsingLine, PdfParsingResDTO pdfParsingResDTO) {
        long sumJeonse_deposit = 0;
        long sumMax_mortgageBond = 0;
        long sumPledge = 0;
        int mortgageCount = 0;
        int pledgeCount = 0;

        if (parsingLine.contains("근저당권변경")) {
            long previousAmount = 0;
            String[] parsingArray = parsingLine.split("\n");
            for (String line : parsingArray) {
                if (line.contains("채권최고액")) {
                    String[] words = line.split(" ");
                    long amount = Long.parseLong(words[5].replaceAll("[^0-9]", ""));
                    if (line.contains("근저당권변경")) {
                        sumMax_mortgageBond -= previousAmount;
                        mortgageCount--;
                    }
                    sumMax_mortgageBond += amount;
                    mortgageCount++;
                    previousAmount = amount;
                }
            }
            pdfParsingResDTO.setMortgageCount(mortgageCount);
            pdfParsingResDTO.setSumMax_mortgageBond(sumMax_mortgageBond);

        } else {
            String regex = "(채권최고액|전세금|채권액)\\s+금(\\d+,?)+원\\s";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(parsingLine);

            while (matcher.find()) {
                String match = matcher.group();
                long value = Long.parseLong(match.replaceAll("[^0-9]", ""));
                if (match.startsWith("전세금")) {
                    sumJeonse_deposit += value;
                } else if (match.startsWith("채권최고액")) {
                    sumMax_mortgageBond += value;
                    mortgageCount++;
                } else if (match.startsWith("채권액")) {
                    sumPledge += value;
                    pledgeCount++;
                }
            }
            pdfParsingResDTO.setSumJeonse_deposit(sumJeonse_deposit);
            pdfParsingResDTO.setSumMax_mortgageBond(sumMax_mortgageBond);
            pdfParsingResDTO.setMortgageCount(mortgageCount);
            pdfParsingResDTO.setSumPledge(sumPledge);
            pdfParsingResDTO.setPledgeCount(pledgeCount);
        }

    }

    public void attachmentNameParsing(String parsingLine, PdfParsingResDTO pdfParsingResDTO) {

        String regex = "(?<=채권자\\s{2})\\S+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(parsingLine);
        Map<Integer, String> attachmentName = new HashMap<>();
        int count = 1;
        while (matcher.find()) {
            attachmentName.put(count, matcher.group());
            count++;
        }
        pdfParsingResDTO.setAttachmentList(attachmentName);
    }

    public void mortgageNameParsing(String parsingLine, PdfParsingResDTO pdfParsingResDTO) {

        String regex = "(?<=근저당권자\\s{2})\\S+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(parsingLine);
        Map<Integer, String> mortgagee = new HashMap<>();
        int count = 1;
        while (matcher.find()) {
            mortgagee.put(count, matcher.group());
            count++;
        }
        pdfParsingResDTO.setMortgageeList(mortgagee);
    }

    public void jeonseNameParsing(String parsingLine, PdfParsingResDTO pdfParsingResDTO) {

        String regex = "(?<=전세권자\\s{2})\\S+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(parsingLine);
        Map<Integer, String> jeonseAuthority = new HashMap<>();
        int count = 1;
        while (matcher.find()) {
            jeonseAuthority.put(count, matcher.group());
            count++;
        }
        pdfParsingResDTO.setJeonseAuthorityList(jeonseAuthority);
    }

    public void bondCreditorParsing(String parsingLine, PdfParsingResDTO pdfParsingResDTO) {

        String regex = "(?<=채권자\\s{2})\\S+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(parsingLine);
        Map<Integer, String> bondCreditor = new HashMap<>();
        int count = 1;
        while (matcher.find()) {
            bondCreditor.put(count, matcher.group());
            count++;
        }
        pdfParsingResDTO.setPledgeCreditorList(bondCreditor);
    }

    public void printingDateParsing(String parsingLine, PdfParsingResDTO pdfParsingResDTO) {
        String[] parsingLineList = parsingLine.split("\r\n");
        String printTime = parsingLineList[parsingLineList.length - 1].substring(7).trim();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
        LocalDateTime dt = LocalDateTime.parse(printTime, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        pdfParsingResDTO.setPrintingDate(dt.format(outputFormatter));
    }
}
