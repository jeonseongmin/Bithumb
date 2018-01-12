package com.bithumb.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class OrderBook {
	private String orderCurrency;
	private String paymentCurrency;
	private String timestamp;
	private String dateString;
	private List<Asks> asksList;
	private List<Bids> bidsList;
	
	public String getDateString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		long date = Long.parseLong(this.getTimestamp());
	    Date currDate = new Date(date);
	    String strDate = simpleDateFormat.format(currDate);
	    return strDate;
	}

	@Override
	public String toString() {
		return "OrderBook ["
				+ "\norderCurrency=" + orderCurrency + ", paymentCurrency=" + paymentCurrency + ", timestamp=" + timestamp + ", dateString=" + dateString
				+ "\n, asksList=" + asksList
				+ "\n, bidsList=" + bidsList
				+ "\n]";
	}
	
}
