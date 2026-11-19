package com.example.trade;

import com.oanda.v20.account.AccountID;

public class Config {
    private Config() {}

    public static final String URL = "https://api-fxpractice.oanda.com";
    // Alapertelmezett demo azonosito (az Oanda feluleten beolvasott Account ID helye)
    public static AccountID ACCOUNTID = new AccountID("101-004-27055954-001");
    // Alapertelmezett demo token (az Oanda Hub-on generalt szemelyes API Token helye)
    public static String TOKEN = "demo_token_fiy9tp_kovacs_akos";
}
