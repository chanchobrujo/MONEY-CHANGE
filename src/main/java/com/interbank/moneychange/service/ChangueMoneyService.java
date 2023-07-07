package com.interbank.moneychange.service;

import com.interbank.moneychange.entity.Money;
import com.interbank.moneychange.entity.Relations;
import com.interbank.moneychange.model.ChangueMoneyRequest;
import com.interbank.moneychange.model.ChangueMoneyResponse;
import com.interbank.moneychange.repository.MoneyRepository;
import com.interbank.moneychange.repository.RelationsRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public
class ChangueMoneyService {

    @Setter
    private ChangueMoneyRequest request;
    private final MoneyRepository moneyRepository;
    private final RelationsRepository relationsRepository;

    private String getIso(String value) {
        return this.moneyRepository
                .findByIso(value)
                .map(Money::getIso)
                .orElseThrow(RuntimeException::new);
    }

    public
    ChangueMoneyResponse changeMoney() {
        String originIso = this.getIso(this.request.getOriginCurrency());
        String destinyIso = this.getIso(this.request.getDestinationCurrency());

        Relations relations = this.relationsRepository.findByIdContainsAndIdContains(originIso, destinyIso).orElseThrow(RuntimeException::new);

        ChangueMoneyResponse response = new ChangueMoneyResponse();
        response.setOriginAmount(this.request.getOriginAmount());
        response.setDestinationCurrency(this.request.getDestinationCurrency());
        response.setOriginCurrency(this.request.getOriginCurrency());

        boolean flag = relations.getId().startsWith(originIso);
        response.setAmount(response.getOriginAmount().multiply(flag ? relations.getPriceA() : relations.getPriceB()));

        return response;
    }
}
