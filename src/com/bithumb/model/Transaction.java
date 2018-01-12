package com.bithumb.model;

import lombok.Data;

@Data
public class Transaction {
	private String transactionDate;
	private String type;
	private String unitsTraded;
	private String price;
	private String total;
	
	@Override
	public String toString() {
		return "\nTransaction [transactionDate=" + transactionDate + ", type=" + type + ", unitsTraded=" + unitsTraded + ", price=" + price + ", total=" + total + "]";
	}
	
}
