package com.igaming.wallet_api;

public class SafeWallet {
    private long balance;

    public SafeWallet(long balance) {
        this.balance = balance;
    }
    public synchronized void debit(long amount){
        if (balance<amount){
            throw new IllegalArgumentException("Heт денег");
        }
        balance-=amount;
    }

    public long getBalance() {
        return balance;
    }
}
