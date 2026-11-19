package com.example.tradepulse.model;

public class ExchangeRateItem {
    private String date;
    private String currency;
    private int unit;
    private double rate;

    public ExchangeRateItem() {}

    public ExchangeRateItem(String date, String currency, int unit, double rate) {
        this.date = date;
        this.currency = currency;
        this.unit = unit;
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
