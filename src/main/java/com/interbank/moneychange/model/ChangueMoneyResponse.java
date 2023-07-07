package com.interbank.moneychange.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public
class ChangueMoneyResponse extends ChangueMoneyRequest {
    private BigDecimal amount;
}
