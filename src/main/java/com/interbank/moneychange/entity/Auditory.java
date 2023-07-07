package com.interbank.moneychange.entity;

import com.interbank.moneychange.utils.GeneralUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@Table(name = "auditory")
public class Auditory {
    @Id
    private String id;
    @Column(nullable = false)
    private String user_;
    @Column(nullable = false)
    private boolean success;
    private String message;
    private String error;
    private LocalDateTime localDateTime;

    public Auditory() {
        this.id = UUID.randomUUID().toString();
        this.localDateTime = GeneralUtils.zoneSystemDefault().toLocalDateTime();
    }
}
