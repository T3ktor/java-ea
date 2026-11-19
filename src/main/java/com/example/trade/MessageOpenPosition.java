package com.example.trade;

public class MessageOpenPosition {
    private String instrument = "EUR_USD";
    private int units = 10;

    public MessageOpenPosition() {}

    public MessageOpenPosition(String instrument, int units) {
        this.instrument = instrument;
        this.units = units;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
