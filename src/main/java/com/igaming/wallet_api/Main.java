package com.igaming.wallet_api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Создаём кошелёк
        Wallet wallet = new Wallet("user-1", 1000L);
        System.out.println("Баланс: " + wallet.getBalance()); // 1000

        // Пополняем
        wallet.deposit(500L);
        System.out.println("После deposit: " + wallet.getBalance()); // 1500

        // Списываем
        wallet.debit(200L);
        System.out.println("После debit: " + wallet.getBalance()); // 1300

        try {
            wallet.debit(9999L);
        } catch (InsufficientFundsException e) {
            System.out.println("Нет денег: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Неверный аргумент: " + e.getMessage());

        } catch (Exception e) {
            // ловит ВСЁ остальное — ставь всегда последним
            System.out.println("Неизвестная ошибка: " + e.getMessage());
        }

        List<Transaction> history = new ArrayList<>();
        history.add(new Transaction("tx-001", 500L, "user-1", "DEBIT"));
        history.add(new Transaction("tx-002", 1000L, "user-1", "CREDIT"));
        history.add(new Transaction("tx-003", 300L, "user-2", "DEBIT"));
        history.add(new Transaction("tx-004", 800L, "user-1", "DEBIT"));

        long totalDebited = history.stream()
                .filter(tx -> tx.getUserId().equals("user-1"))
                .filter(tx -> tx.getType().equals("DEBIT"))
                .mapToLong(Transaction::getAmount)
                .sum();

        List<Transaction> user2Txs = history.stream()
                .filter(tx -> tx.getUserId().equals("user-2"))
                .collect(Collectors.toList());
        System.out.println("Транзакций user-2: " + user2Txs.size());

        boolean hasCredits = history.stream()
                .anyMatch(tx -> tx.getType().equals("CREDIT"));

        System.out.println("Есть кредиты: " + hasCredits);
        System.out.println("_____________________________");

        UnsafeWallet unsafe = new UnsafeWallet(1000L);
        Thread thread1 = new Thread(() -> unsafe.debit(800L));
        Thread thread2 = new Thread(() -> unsafe.debit(800L));
        thread1.start();
        thread2.start();
        thread1.join();
        thread1.join();
        System.out.println("Небезопасный баланс: " + unsafe.getBalance());


        System.out.println("_____________________________");



        SafeWallet safe = new SafeWallet(1000L);

        Thread t3 = new Thread(() ->
        { try { safe.debit(800L);
            System.out.println("t3 снял 800");
        } catch (Exception e)
        { System.out.println("t3: " + e.getMessage());} });

        Thread t4 = new Thread(() ->
        { try { safe.debit(800L);
            System.out.println("t4 снял 800");
        } catch (Exception e)
        { System.out.println("t4: " + e.getMessage());} });


        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println("Безопасный баланс: " + safe.getBalance());
    }}