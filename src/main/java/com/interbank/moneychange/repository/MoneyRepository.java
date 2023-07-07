package com.interbank.moneychange.repository;

import com.interbank.moneychange.entity.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public
interface MoneyRepository extends JpaRepository<Money, String> {
    Optional<Money> findByIso (String iso);
}
