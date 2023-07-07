package com.interbank.moneychange.controller;

import com.interbank.moneychange.model.ChangueMoneyRequest;
import com.interbank.moneychange.model.ChangueMoneyResponse;
import com.interbank.moneychange.service.ChangueMoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.apache.tomcat.websocket.Constants.AUTHORIZATION_HEADER_NAME;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public
class ChangueMoneyController {

    private final
    ChangueMoneyService service;

    @PostMapping("change-money")
    public
    ResponseEntity<ChangueMoneyResponse> changeMoney(
            @RequestHeader(AUTHORIZATION_HEADER_NAME) String token,
            @Valid @RequestBody ChangueMoneyRequest request) {
        this.service.setRequest(request);
        return ResponseEntity.ok(this.service.changeMoney());
    }
}
