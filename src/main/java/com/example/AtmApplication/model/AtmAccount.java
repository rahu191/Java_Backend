package com.example.AtmApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "AtmAccount")
@NoArgsConstructor
@AllArgsConstructor
public class AtmAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Account_id;
    private double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private AtmUser atmUser1;

    public AtmUser getAtmUser1() {
        return atmUser1;
    }

    public void setAtmUser1(AtmUser atmUser1) {
        this.atmUser1 = atmUser1;
    }

    public long getAccount_id() {
        return Account_id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccount_id(long account_id) {
        Account_id = account_id;
    }

    public AtmAccount(double balance, AtmUser atmUser1) {
        this.balance = balance;
        this.atmUser1 = atmUser1;
    }
}
