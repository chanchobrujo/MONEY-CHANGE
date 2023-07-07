package com.interbank.moneychange.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "relations")
public
class Relations {
    @Id
    private String id;
    private BigDecimal priceA;
    private BigDecimal priceB;
}
