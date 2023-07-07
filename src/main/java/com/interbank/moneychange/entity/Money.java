package com.interbank.moneychange.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@Table(name = "money")
public
class Money {
    @Id
    private String id;
    @Column(unique = true)
    private String iso;
    @Column(unique = true)
    private String country;

    public Money() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public
    boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Money money = (Money) o;
        return id != null && Objects.equals(id, money.id);
    }

    @Override
    public
    int hashCode () {
        return getClass().hashCode();
    }
}
