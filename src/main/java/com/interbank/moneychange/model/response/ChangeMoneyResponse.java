package com.interbank.moneychange.model.response;

import com.interbank.moneychange.model.request.ChangeMoneyRequest;
import lombok.Data;

@Data
public
class ChangeMoneyResponse extends ChangeMoneyRequest {
    private String value;
}
