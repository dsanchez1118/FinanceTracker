package com.financetracker.app;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId; // From Plaid
    private String merchantName;
    private Double amount;
    private LocalDate date;

    public String getMerchant() {
        return this.merchantName;
    }

    public LocalDate getAmount() {
        return this.date;
    }
}
