package com.bithumb.model;

import java.util.List;

import lombok.Data;

@Data
public class RecentTransactions {
	private List<Transaction> transactionList;
}
