package com.example.trade;

public class MessageClosePosition {
    private int tradeId;

    public MessageClosePosition() {}

    public MessageClosePosition(int tradeId) {
        this.tradeId = tradeId;
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }
}
