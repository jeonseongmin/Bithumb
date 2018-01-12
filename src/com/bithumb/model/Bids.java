package com.bithumb.model;

import lombok.Data;

@Data
public class Bids {
	private String quantity;
	private String price;
	
	@Override
	public String toString() {
		return "\nBids [quantity=" + quantity + ", price=" + price + "]";
	}
}
