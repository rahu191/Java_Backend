package com.example.bankingassignment.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue
    private long accountId;
    private double balance;

    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER)
    private User accountOwner;


    public Account() {
    }

    public Account(long accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public User getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(User accountOwner) {
        this.accountOwner = accountOwner;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
