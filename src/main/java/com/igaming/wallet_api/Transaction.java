package com.igaming.wallet_api;

public class Transaction {
    private String id;
    private long amount;
    private String userId;
    private String type;

    public String getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }

    public String getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public Transaction(String id, long amount, String userId, String type) {
        this.id = id;
        this.amount = amount;
        this.userId = userId;
        this.type = type;
    }


}
