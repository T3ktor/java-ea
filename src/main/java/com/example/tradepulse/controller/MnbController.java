package com.example.tradepulse.controller;

import com.example.tradepulse.model.ExchangeRateItem;
import com.example.tradepulse.model.MessagePrice;
import com.example.tradepulse.service.MnbSoapService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import soapclient.MNBArfolyamServiceSoap;
import soapclient.MNBArfolyamServiceSoapImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MnbController {

    private final MnbSoapService mnbSoapService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MnbController(MnbSoapService mnbSoapService) {
        this.mnbSoapService = mnbSoapService;
    }

    // Fő reszponzív SOAP menüpont Chart.js grafikonnal
    @GetMapping("/soap/mnb")
    public String showMnbForm(Model model) {
        model.addAttribute("messagePrice", new MessagePrice());
        return "soap/form";
    }

    @PostMapping("/soap/mnb")
    public String queryMnbRates(@ModelAttribute MessagePrice messagePrice, Model model) {
        List<ExchangeRateItem> items = mnbSoapService.getParsedExchangeRates(
                messagePrice.getStartDate(),
                messagePrice.getEndDate(),
                messagePrice.getCurrency()
        );

        List<String> dates = new ArrayList<>();
        List<Double> rates = new ArrayList<>();

        for (ExchangeRateItem item : items) {
            dates.add(item.getDate());
            rates.add(item.getRate());
        }

        try {
            model.addAttribute("chartLabelsJson", objectMapper.writeValueAsString(dates));
            model.addAttribute("chartDataJson", objectMapper.writeValueAsString(rates));
        } catch (Exception e) {
            model.addAttribute("chartLabelsJson", "[]");
            model.addAttribute("chartDataJson", "[]");
        }

        model.addAttribute("messagePrice", messagePrice);
        model.addAttribute("items", items);
        model.addAttribute("rawXml", mnbSoapService.getRawExchangeRates(messagePrice.getStartDate(), messagePrice.getEndDate(), messagePrice.getCurrency()));

        return "soap/result";
    }

    // Tanári PDF (34. oldal) szerinti /feladat1 végpont
    @GetMapping("/feladat1")
    @ResponseBody
    public String feladat1() throws Exception {
        MNBArfolyamServiceSoapImpl impl = new MNBArfolyamServiceSoapImpl();
        MNBArfolyamServiceSoap service = impl.getCustomBindingMNBArfolyamServiceSoap();
        return service.getInfo() + "<br>" +
                service.getCurrentExchangeRates() + "<br>" +
                service.getExchangeRates("2022-08-14", "2022-09-14", "EUR");
    }

    // Tanári PDF (35-37. oldal) szerinti /exercise végpontok
    @GetMapping("/exercise")
    public String exerciseGet(Model model) {
        model.addAttribute("param", new MessagePrice());
        return "soap/exercise_form";
    }

    @PostMapping("/exercise")
    public String exercisePost(@ModelAttribute("param") MessagePrice messagePrice, Model model) throws Exception {
        MNBArfolyamServiceSoapImpl impl = new MNBArfolyamServiceSoapImpl();
        MNBArfolyamServiceSoap service = impl.getCustomBindingMNBArfolyamServiceSoap();
        String strOut = "Currency:" + messagePrice.getCurrency() + ";" +
                "Start date:" + messagePrice.getStartDate() + ";" +
                "End date:" + messagePrice.getEndDate() + ";";
        strOut += service.getExchangeRates(messagePrice.getStartDate(), messagePrice.getEndDate(), messagePrice.getCurrency());
        model.addAttribute("sendOut", strOut);
        return "soap/exercise_result";
    }
}
