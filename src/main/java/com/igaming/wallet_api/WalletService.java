package com.igaming.wallet_api;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WalletService {

    private Map<String,Long> balances = new HashMap<>();


    public WalletService (){
        //test Data
        balances.put("user-1",5000L);
        balances.put("user-2",1200L);
    }

    public long getBalance(String userId){
        return balances.getOrDefault(userId,0L);
    }

    public void debit (String userId, long amount) {
        long balance = getBalance(userId);
        if (balance< amount) {
            throw new InsufficientFundsException(amount,balance);
        }
        balances.put(userId, balance-amount);
    }

    public void credit (String userId, long amount){
        long balance = getBalance(userId);
        balances.put(userId, balance+ amount);
    }
}
