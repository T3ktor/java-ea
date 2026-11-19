package com.example.tradepulse.service;

import com.example.tradepulse.model.ExchangeRateItem;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import soapclient.MNBArfolyamServiceSoap;
import soapclient.MNBArfolyamServiceSoapImpl;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MnbSoapService {

    private final MNBArfolyamServiceSoap mnbSoapClient;

    public MnbSoapService() {
        MNBArfolyamServiceSoapImpl impl = new MNBArfolyamServiceSoapImpl();
        this.mnbSoapClient = impl.getCustomBindingMNBArfolyamServiceSoap();
    }

    public String getRawExchangeRates(String startDate, String endDate, String currency) {
        try {
            return mnbSoapClient.getExchangeRates(startDate, endDate, currency);
        } catch (Exception e) {
            return "<error>" + e.getMessage() + "</error>";
        }
    }

    public List<ExchangeRateItem> getParsedExchangeRates(String startDate, String endDate, String currency) {
        List<ExchangeRateItem> items = new ArrayList<>();
        String xml = getRawExchangeRates(startDate, endDate, currency);

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));

            NodeList dayNodes = doc.getElementsByTagName("Day");
            for (int i = 0; i < dayNodes.getLength(); i++) {
                Element dayElem = (Element) dayNodes.item(i);
                String date = dayElem.getAttribute("date");

                NodeList rateNodes = dayElem.getElementsByTagName("Rate");
                for (int j = 0; j < rateNodes.getLength(); j++) {
                    Element rateElem = (Element) rateNodes.item(j);
                    String curr = rateElem.getAttribute("curr");
                    String unitStr = rateElem.getAttribute("unit");
                    int unit = unitStr != null && !unitStr.isEmpty() ? Integer.parseInt(unitStr) : 1;
                    String rateText = rateElem.getTextContent().trim().replace(',', '.');
                    double rate = Double.parseDouble(rateText);

                    items.add(new ExchangeRateItem(date, curr, unit, rate));
                }
            }
        } catch (Exception e) {
            // Nem sikerult a DOM parsolas vagy ures az eredmeny
        }

        return items;
    }

    public String getCurrentRates() {
        try {
            return mnbSoapClient.getCurrentExchangeRates();
        } catch (Exception e) {
            return "<error>" + e.getMessage() + "</error>";
        }
    }

    public String getInfo() {
        try {
            return mnbSoapClient.getInfo();
        } catch (Exception e) {
            return "<error>" + e.getMessage() + "</error>";
        }
    }
}
