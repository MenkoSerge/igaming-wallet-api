package com.igaming.wallet_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
// Говорит JUnit использовать Mockito
class WalletServiceTest {

    @Mock
    WalletRepository walletRepository;
    // Фейковый репозиторий — не идёт в БД

    @Mock
    TransactionRepository transactionRepository;
    // Фейковый репозиторий транзакций

    @InjectMocks
    WalletService walletService;
    // Реальный сервис — Mockito передаст ему моки выше

    WalletEntity testWallet;

    @BeforeEach
        // Выполняется перед каждым тестом
    void setUp() {
        testWallet = new WalletEntity("user-1", 5000L);
    }

    @Test
        // Тест 1: получить баланс существующего пользователя
    void getBalance_existingUser_returnsBalance() {
        // ARRANGE — подготовка
        // Говорим моку: когда спросят user-1 — верни testWallet
        when(walletRepository.findById("user-1"))
                .thenReturn(Optional.of(testWallet));

        // ACT — действие
        long balance = walletService.getBalance("user-1");

        // ASSERT — проверка
        assertEquals(5000L, balance);
    }

    @Test
        // Тест 2: пользователь не найден — должно бросить исключение
    void getBalance_userNotFound_throwsException() {
        // Говорим моку: когда спросят user-999 — вернуть пусто
        when(walletRepository.findById("user-999"))
                .thenReturn(Optional.empty());

        // Проверяем что бросается исключение
        assertThrows(RuntimeException.class, () -> {
            walletService.getBalance("user-999");
        });
    }

    @Test
        // Тест 3: успешное списание
    void debit_sufficientFunds_reducesBalance() {
        // Arrange
        when(walletRepository.findById("user-1"))
                .thenReturn(Optional.of(testWallet));
        when(transactionRepository.existsById("tx-001"))
                .thenReturn(false); // транзакция новая

        // Act
        long newBalance = walletService.debit("user-1", 1000L, "tx-001");

        // Assert
        assertEquals(4000L, newBalance);
        // Проверяем что save был вызван один раз
        verify(walletRepository, times(1)).save(testWallet);
    }

    @Test
        // Тест 4: недостаточно средств — должно бросить исключение
    void debit_insufficientFunds_throwsException() {
        when(walletRepository.findById("user-1"))
                .thenReturn(Optional.of(testWallet));
        when(transactionRepository.existsById("tx-002"))
                .thenReturn(false);

        // Баланс 5000, пытаемся списать 9999
        assertThrows(InsufficientFundsException.class, () -> {
            walletService.debit("user-1", 9999L, "tx-002");
        });
    }

    @Test
        // Тест 5: идемпотентность — повторная транзакция игнорируется
    void debit_duplicateTransaction_returnsCurrentBalance() {
        when(walletRepository.findById("user-1"))
                .thenReturn(Optional.of(testWallet));
        when(transactionRepository.existsById("tx-001"))
                .thenReturn(true); // транзакция уже обработана!

        long balance = walletService.debit("user-1", 1000L, "tx-001");

        // Баланс не изменился
        assertEquals(5000L, balance);
        // save НЕ должен был вызываться
        verify(walletRepository, never()).save(any());
    }
}