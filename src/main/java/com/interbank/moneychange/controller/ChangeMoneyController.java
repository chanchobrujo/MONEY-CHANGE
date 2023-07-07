package com.interbank.moneychange.controller;

import com.interbank.moneychange.model.request.ChangeMoneyRequest;
import com.interbank.moneychange.model.response.ChangeMoneyResponse;
import com.interbank.moneychange.model.response.MoneyResponse;
import com.interbank.moneychange.service.ChangeMoneyService;
import com.interbank.moneychange.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.apache.tomcat.websocket.Constants.AUTHORIZATION_HEADER_NAME;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public
class ChangeMoneyController {

    private final
    ChangeMoneyService service;

    @PostMapping("change-money")
    public
    ResponseEntity<ChangeMoneyResponse> changeMoney(
            @RequestHeader(AUTHORIZATION_HEADER_NAME) String token,
            @Valid @RequestBody ChangeMoneyRequest request) {
        if (!SecurityUtils.validateToken(token)) {
            throw new RuntimeException("Error");
        }
        this.service.setToken(token);
        this.service.setRequest(request);
        return ResponseEntity.ok(this.service.changeMoney());
    }

    @GetMapping("available-currencies")
    public
    ResponseEntity<List<MoneyResponse>> availableCurrencies() {
        return ResponseEntity.ok(this.service.retrieveCurrencies());
    }
}
