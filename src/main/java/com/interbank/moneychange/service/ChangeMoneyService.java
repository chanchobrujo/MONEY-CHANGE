package com.interbank.moneychange.service;

import com.interbank.moneychange.entity.Money;
import com.interbank.moneychange.entity.Relations;
import com.interbank.moneychange.model.request.ChangeMoneyRequest;
import com.interbank.moneychange.model.response.ChangeMoneyResponse;
import com.interbank.moneychange.model.response.MoneyResponse;
import com.interbank.moneychange.repository.MoneyRepository;
import com.interbank.moneychange.repository.RelationsRepository;
import com.interbank.moneychange.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public
class ChangeMoneyService {

    @Setter
    private String token;
    @Setter
    private ChangeMoneyRequest request;
    private final MoneyRepository moneyRepository;
    private final RelationsRepository relationsRepository;
    private final AuditoryRegisterService service;

    private Money getMoney(String value) {
        return this.moneyRepository
                .findByIso(value)
                .orElseThrow(() -> new RuntimeException("Valor de moneda '"+value+"' inválido."));
    }

    public ChangeMoneyResponse changeMoney() {
        this.service.setToken(this.token);
        try {
            log.info("Usuario que realizó el consumo: " + SecurityUtils.decryptToken(this.token));
            Money originMoney = this.getMoney(this.request.getOriginCurrency());
            Money destinyMoney = this.getMoney(this.request.getDestinationCurrency());

            Relations relations = this.relationsRepository
                    .findByIdContainsAndIdContains(originMoney.getIso(), destinyMoney.getIso())
                    .orElseThrow(() -> new RuntimeException("Cambio de moneda no disponible."));

            ChangeMoneyResponse response = new ChangeMoneyResponse();
            response.setOriginAmount(this.request.getOriginAmount());
            response.setDestinationCurrency(this.request.getDestinationCurrency());
            response.setOriginCurrency(this.request.getOriginCurrency());

            boolean flag = relations.getId().startsWith(originMoney.getIso());
            BigDecimal amount = response.getOriginAmount().multiply(flag ? relations.getPriceA() : relations.getPriceB());
            response.setValue(destinyMoney.getSymbol() + " " + amount);
            this.service.saveAuditory(null, "Cambio realizado", true);
            return response;
        } catch (Exception e) {
            this.service.saveAuditory(e.getMessage(), null, false);
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<MoneyResponse> retrieveCurrencies() {
        return this.moneyRepository.findAll()
                .stream()
                .map(money -> {
                    MoneyResponse response = new MoneyResponse();
                    response.setCountry(money.getCountry());
                    response.setSymbol(money.getSymbol());
                    response.setIso(money.getIso());
                    return response;
                })
                .collect(Collectors.toList());
    }
}
