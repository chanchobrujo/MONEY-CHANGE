package com.interbank.moneychange.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public
class ChangueMoneyRequest {
    private BigDecimal originAmount;
    @NotBlank
    private String originCurrency;
    @NotBlank
    private String destinationCurrency;
}
