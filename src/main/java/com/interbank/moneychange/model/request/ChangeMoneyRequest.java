package com.interbank.moneychange.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public
class ChangeMoneyRequest {
    private BigDecimal originAmount;
    @NotBlank
    private String originCurrency;
    @NotBlank
    private String destinationCurrency;
}
