package com.igaming.wallet_api;

public class Main {

    public static void main(String[] args) {

        // Создаём кошелёк
        Wallet wallet = new Wallet("user-1", 1000L);
        System.out.println("Баланс: " + wallet.getBalance()); // 1000

        // Пополняем
        wallet.deposit(500L);
        System.out.println("После deposit: " + wallet.getBalance()); // 1500

        // Списываем
        wallet.debit(200L);
        System.out.println("После debit: " + wallet.getBalance()); // 1300
    }
}