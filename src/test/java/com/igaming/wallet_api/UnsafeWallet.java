package com.igaming.wallet_api;

public class UnsafeWallet {public long balance; public UnsafeWallet(long balance)
{this.balance=balance;}
    public void debit (long amount ){
    if (balance>=amount){balance-=amount;}}

    public long getBalance() {
        return balance;
    }
}
