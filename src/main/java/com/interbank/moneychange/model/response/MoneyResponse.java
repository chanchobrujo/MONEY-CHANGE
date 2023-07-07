package com.interbank.moneychange.model.response;

import lombok.Data;

@Data
public class MoneyResponse {
    private String iso;
    private String country;
    private String symbol;
}
