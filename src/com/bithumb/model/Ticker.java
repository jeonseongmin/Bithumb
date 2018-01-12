package com.bithumb.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Ticker {
	private String openingPrice;
	private String closingPrice;
	private String minPrice;
	private String maxPrice;
	private String averagePrice;
	private String unitsTraded;
	private String volume1Day;
	private String volume7Day;
	private String buyPrice;
	private String sellPrice;
	private String date;
	private String dateString;
	
	public String getDateString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		long date = Long.parseLong(this.getDate());
	    Date currDate = new Date(date);
	    String strDate = simpleDateFormat.format(currDate);
	    return strDate;
	}
	
}
