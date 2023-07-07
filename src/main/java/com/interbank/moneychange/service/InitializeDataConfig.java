package com.interbank.moneychange.service;

import com.interbank.moneychange.entity.Money;
import com.interbank.moneychange.entity.Relations;
import com.interbank.moneychange.repository.MoneyRepository;
import com.interbank.moneychange.repository.RelationsRepository;
import com.interbank.moneychange.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Stream;

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
        if (this.moneyRepository.count() == 0 || this.relationsRepository.count() == 0) {
            Money pen = new Money();
            pen.setIso("PEN");
            pen.setCountry("PERU");
            pen.setSymbol("S/");

            Money rub = new Money();
            rub.setIso("RUB");
            rub.setCountry("RUSIA");
            rub.setSymbol("₽");

            Money eur = new Money();
            eur.setIso("EUR");
            eur.setCountry("");
            eur.setSymbol("€");
            Stream.of(pen, rub, eur).forEach(moneyRepository::save);

            Relations penRub = new Relations();
            penRub.setId(pen.getIso().concat("_").concat(rub.getIso()));
            penRub.setPriceA(BigDecimal.valueOf(25.10));
            penRub.setPriceB(BigDecimal.valueOf(0.040));

            Relations rubEur = new Relations();
            rubEur.setId(rub.getIso().concat("_").concat(eur.getIso()));
            rubEur.setPriceA(BigDecimal.valueOf(0.0100));
            rubEur.setPriceB(BigDecimal.valueOf(100.21));

            Relations eurPen = new Relations();
            eurPen.setId(eur.getIso().concat("_").concat(pen.getIso()));
            eurPen.setPriceA(BigDecimal.valueOf(3.99));
            eurPen.setPriceB(BigDecimal.valueOf(0.25));

            Stream.of(penRub, rubEur, eurPen).forEach(relationsRepository::save);
        }
        //System.out.println(SecurityUtils.generateTokenWithoutExpiration("USER_1"));
    }
}
