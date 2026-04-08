package com.igaming.wallet_api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Wallet {

    private String userId;
    private long balance;

    public Wallet(String userId, long balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public void deposit(long amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount <= 0");
        balance += amount;
    }

    public void debit(long amount) {
        if (amount > balance)
            throw new InsufficientFundsException(amount, balance);
        balance -= amount;
    }


    public long getBalance() { return balance; }
    public String getUserId() { return userId; }



}