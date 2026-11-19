package com.example.tradepulse.model;

import java.time.LocalDate;

public class MessagePrice {
    private String currency = "EUR";
    private String startDate = LocalDate.now().minusMonths(1).toString();
    private String endDate = LocalDate.now().toString();

    public MessagePrice() {}

    public MessagePrice(String currency, String startDate, String endDate) {
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
