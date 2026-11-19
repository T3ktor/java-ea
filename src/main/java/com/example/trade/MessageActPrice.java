package com.example.trade;

public class MessageActPrice {
    private String instrument = "EUR_USD";

    public MessageActPrice() {}

    public MessageActPrice(String instrument) {
        this.instrument = instrument;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
}
