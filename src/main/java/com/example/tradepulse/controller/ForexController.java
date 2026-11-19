package com.example.tradepulse.controller;

import com.example.trade.*;
import com.example.tradepulse.service.ForexService;
import com.oanda.v20.account.AccountSummary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ForexController {

    private final ForexService forexService;

    public ForexController(ForexService forexService) {
        this.forexService = forexService;
    }

    // 1. Forex Account Info
    @GetMapping("/forex/account")
    public String accountView(Model model) {
        AccountSummary summary = forexService.getAccountSummary();
        model.addAttribute("summary", summary);
        model.addAttribute("accountId", Config.ACCOUNTID.toString());
        return "forex/account";
    }

    // Tanári PDF (41. oldal) /account_info JSON végpont
    @GetMapping("/account_info")
    @ResponseBody
    public Object accountInfoJson() {
        AccountSummary summary = forexService.getAccountSummary();
        if (summary != null) return summary;
        // Szimulalt JSON valasz ha nincs elo Oanda fiok konfiguralva
        return java.util.Map.of(
                "id", Config.ACCOUNTID.toString(),
                "alias", "Primary",
                "currency", "EUR",
                "balance", "100000.0000",
                "unrealizedPL", "12.5000",
                "marginUsed", "150.2500",
                "marginAvailable", "99849.7500",
                "openTradeCount", forexService.getOpenPositions().size()
        );
    }

    // 2. Aktuális árak
    @GetMapping({"/forex/actual-prices", "/actual_prices"})
    public String actualPricesForm(Model model) {
        model.addAttribute("par", new MessageActPrice());
        return "forex/actual_prices";
    }

    @PostMapping({"/forex/actual-prices", "/actual_prices"})
    public String actualPricesSubmit(@ModelAttribute MessageActPrice messageActPrice, Model model) {
        String priceOutput = forexService.getActualPrice(messageActPrice);
        model.addAttribute("instr", messageActPrice.getInstrument());
        model.addAttribute("price", priceOutput);
        return "forex/result_actual_prices";
    }

    // 3. Historikus árak (10 db)
    @GetMapping({"/forex/hist-prices", "/hist_prices"})
    public String histPricesForm(Model model) {
        model.addAttribute("param", new MessageHistPrice());
        return "forex/hist_prices";
    }

    @PostMapping({"/forex/hist-prices", "/hist_prices"})
    public String histPricesSubmit(@ModelAttribute MessageHistPrice messageHistPrice, Model model) {
        List<String> candles = forexService.getHistoricalCandles(messageHistPrice);
        StringBuilder sb = new StringBuilder();
        for (String c : candles) {
            sb.append(c).append(";\n");
        }
        model.addAttribute("instr", messageHistPrice.getInstrument());
        model.addAttribute("granularity", messageHistPrice.getGranularity());
        model.addAttribute("price", sb.toString());
        model.addAttribute("candles", candles);
        return "forex/result_hist_prices";
    }

    // 4. Pozíció nyitás
    @GetMapping({"/forex/open-position", "/open_position"})
    public String openPositionForm(Model model) {
        model.addAttribute("param", new MessageOpenPosition());
        return "forex/open_position";
    }

    @PostMapping({"/forex/open-position", "/open_position"})
    public String openPositionSubmit(@ModelAttribute MessageOpenPosition messageOpenPosition, Model model) {
        String result = forexService.openPosition(messageOpenPosition);
        model.addAttribute("instr", messageOpenPosition.getInstrument());
        model.addAttribute("units", messageOpenPosition.getUnits());
        model.addAttribute("id", result);
        return "forex/result_open_position";
    }

    // 5. Nyitott pozíciók listázása
    @GetMapping({"/forex/positions"})
    public String positionsView(Model model) {
        model.addAttribute("trades", forexService.getOpenPositions());
        return "forex/positions";
    }

    // Tanári PDF (50. oldal) szerinti /positions nyers HTML kiíratás
    @GetMapping("/positions")
    @ResponseBody
    public String positionsRaw() {
        StringBuilder sb = new StringBuilder("Open positions:<br>");
        for (ForexService.SimTrade t : forexService.getOpenPositions()) {
            sb.append(t.getId()).append(", ")
                    .append(t.getInstrument()).append(", ")
                    .append(t.getOpenTime()).append(", ")
                    .append(t.getCurrentUnits()).append(", ")
                    .append(t.getPrice()).append(", ")
                    .append(t.getUnrealizedPL()).append("<br>");
        }
        return sb.toString();
    }

    // 6. Pozíció zárás
    @GetMapping({"/forex/close-position", "/close_position"})
    public String closePositionForm(Model model) {
        model.addAttribute("param", new MessageClosePosition());
        model.addAttribute("openTrades", forexService.getOpenPositions());
        return "forex/close_position";
    }

    @PostMapping({"/forex/close-position", "/close_position"})
    public String closePositionSubmit(@ModelAttribute MessageClosePosition messageClosePosition, Model model) {
        String result = forexService.closePosition(messageClosePosition);
        model.addAttribute("tradeId", result);
        return "forex/result_close_position";
    }
}
