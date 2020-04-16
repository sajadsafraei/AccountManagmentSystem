package com.sajad.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter
@Getter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRANSACTION_ID", nullable = false, updatable = false)
    private Long id;

    private Date date;
    private String description;

    private double amount;
    private BigDecimal availableBalance;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

}
