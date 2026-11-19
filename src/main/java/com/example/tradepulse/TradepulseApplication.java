package com.example.tradepulse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example", "soapclient"})
public class TradepulseApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradepulseApplication.class, args);
    }
}
