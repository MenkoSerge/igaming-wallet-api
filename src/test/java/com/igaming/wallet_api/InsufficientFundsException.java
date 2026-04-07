package com.igaming.wallet_api;

public class InsufficientFundsException extends RuntimeException {

    private final long requested;
    private final long available;

    public InsufficientFundsException(long requested, long available) {
        super("Insufficient funds. Requested: " + requested
                + ", available: " + available);
        this.requested = requested;
        this.available = available;
    }

    public long getRequested() { return requested; }
    public long getAvailable() { return available; }
}