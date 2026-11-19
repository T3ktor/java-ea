package com.example.trade;

public class MessageHistPrice {
    private String instrument = "EUR_USD";
    private String granularity = "D";

    public MessageHistPrice() {}

    public MessageHistPrice(String instrument, String granularity) {
        this.instrument = instrument;
        this.granularity = granularity;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getGranularity() {
        return granularity;
    }

    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }
}
