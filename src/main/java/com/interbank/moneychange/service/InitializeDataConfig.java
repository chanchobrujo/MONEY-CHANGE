package com.interbank.moneychange.service;

import com.interbank.moneychange.entity.Money;
import com.interbank.moneychange.entity.Relations;
import com.interbank.moneychange.repository.MoneyRepository;
import com.interbank.moneychange.repository.RelationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static java.math.BigDecimal.valueOf;

@Component
@RequiredArgsConstructor
public
class InitializeDataConfig implements CommandLineRunner {

    private final
    MoneyRepository moneyRepository;

    private final
    RelationsRepository relationsRepository;

    @Override
    public
    void run (String... args) {
    }
}
