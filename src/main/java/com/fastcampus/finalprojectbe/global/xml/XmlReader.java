package com.fastcampus.finalprojectbe.global.xml;


import com.fastcampus.finalprojectbe.openapi.dto.tradingDetailResDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class XmlReader {

    List<tradingDetailResDTO> aptList = new ArrayList<>();

    public void parser(Document doc) {

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("item");


        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            Element element = (Element) nNode;

            aptList.add(new tradingDetailResDTO(
                    getTagValue("일련번호", element),
                    getTagValue("거래금액", element).trim().replace(",", ""),
                    getTagValue("거래유형", element),
                    getTagValue("건축년도", element),
                    getTagValue("년", element),
                    getTagValue("월", element),
                    getTagValue("일", element),
                    getTagValue("법정동시군구코드", element),
                    getTagValue("법정동읍면동코드", element),
                    getTagValue("법정동", element).trim(),
                    getTagValue("아파트", element),
                    getTagValue("지번",element),
                    getTagValue("전용면적", element),
                    getTagValue("층", element)));
        }
    }

    public String getTagValue(String tag, Element element) {

        String result = "";

        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();

        result = nodeList.item(0).getTextContent();

        return result;
    }

    public void clear() {
        aptList.clear();
    }
}
