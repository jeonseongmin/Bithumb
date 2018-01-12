package com.bithumb.model;

import lombok.Data;

@Data
public class Asks {
	private String quantity;
	private String price;
	
	@Override
	public String toString() {
		return "\nAsks [quantity=" + quantity + ", price=" + price + "]";
	}
	
}
