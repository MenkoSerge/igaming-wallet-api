package com.igaming.wallet_api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "wallets")
public class WalletEntity {

    @Id
    private String userId;
    private long balance;

    public WalletEntity(){}


    public WalletEntity(String userId, long balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
