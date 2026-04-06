package com.igaming.wallet_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class WalletApiApplicationTests {

	public static void main(String[] args) {


			//Дано — список с дубликатами:
			List<String> rawTxIds = new ArrayList<>();
			rawTxIds.add("tx-001");
			rawTxIds.add("tx-002");
			rawTxIds.add("tx-001");
			rawTxIds.add("tx-003");
			rawTxIds.add("tx-002");
			// дубликат
			// Задача: получить только уникальные txId
			// Ожидаемый результат: 3 уникальных элемента
			// Подсказка: один Set решает это в одну строку
			Set<String> uniqueTxIds = new HashSet<>(rawTxIds);
			System.out.println(uniqueTxIds.size());
			System.out.println(uniqueTxIds);// 3


			List<Long> amounts = new ArrayList<>();
			amounts.add(500L);
			amounts.add(1200L);
			amounts.add(300L);
			amounts.add(800L);
			long total = 0;

			for (Long amount : amounts) {
				total=total+amount;
			}


			System.out.println(total);

			Map<String, Long> balances = new HashMap<>();
			balances.put("user-1", 5000L);
			balances.put("user-2", 12000L);
			balances.put("user-3", 3000L);
			// Задача: найти userId с максимальным балансом
			// Ожидаемый результат: "user-2"
			String richestUser = null;
			long maxBalance = 0;

			for (Map.Entry<String, Long> stringLongEntry : balances.entrySet()) {
				if(stringLongEntry.getValue()>maxBalance){
					maxBalance = stringLongEntry.getValue();
					richestUser = stringLongEntry.getKey();
				}
			}



			System.out.println(richestUser);
			System.out.println(maxBalance);
			// "user-2"


		}
	}



