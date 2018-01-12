package com.bithumb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bithumb.api.Api_Client;
import com.bithumb.model.Asks;
import com.bithumb.model.Bids;
import com.bithumb.model.OrderBook;
import com.bithumb.model.RecentTransactions;
import com.bithumb.model.Ticker;
import com.bithumb.model.Transaction;

public class PublicController {

	private static final Api_Client API = new Api_Client("api connect key", "api secret key");
	private static final String TICKER_URL = "/public/ticker/BTC";
	private static final String RECENT_URL = "/public/recent_transactions/BTC";
	private static final String ORDER_URL = "/public/orderbook/BTC";
	
	public Ticker getTicker(HashMap<String, String> paramMap) {
		String result = API.callApi(TICKER_URL, paramMap);
		JSONObject jsonObject = new JSONObject(result);
		JSONObject jsonData = jsonObject.getJSONObject("data");
		
		Ticker ticker = new Ticker();
		ticker.setOpeningPrice(jsonData.getString("opening_price"));
		ticker.setOpeningPrice(jsonData.getString("opening_price"));
	    ticker.setClosingPrice(jsonData.getString("closing_price"));
	    ticker.setMinPrice(jsonData.getString("min_price"));
	    ticker.setMaxPrice(jsonData.getString("max_price"));
	    ticker.setAveragePrice(jsonData.getString("average_price"));
	    ticker.setUnitsTraded(jsonData.getString("units_traded"));
	    ticker.setVolume1Day(jsonData.getString("volume_1day"));
	    ticker.setVolume7Day(jsonData.getString("volume_7day"));
	    ticker.setBuyPrice(jsonData.getString("buy_price"));
	    ticker.setSellPrice(jsonData.getString("sell_price"));
	    ticker.setDate(jsonData.getString("date"));
	    
	    System.out.println(ticker.toString());
		
		return ticker;
	}
	
	public OrderBook getOrderBook(HashMap<String, String> paramMap) {
		String result = API.callApi(ORDER_URL, paramMap);
		JSONObject jsonObject = new JSONObject(result);
		JSONObject jsonData = jsonObject.getJSONObject("data");
		
		OrderBook orderBook = new OrderBook();
    	orderBook.setOrderCurrency(jsonData.getString("order_currency"));
    	orderBook.setPaymentCurrency(jsonData.getString("payment_currency"));
    	orderBook.setTimestamp(jsonData.getString("timestamp"));
		
		JSONArray arrAsks = jsonData.getJSONArray("asks");
		List<Asks> asksList = new ArrayList<Asks>();
		for(Object object : arrAsks) {
			JSONObject obj = (JSONObject)object;
			Asks asks = new Asks();
			asks.setQuantity(obj.getString("quantity"));
			asks.setPrice(obj.getString("price"));
			asksList.add(asks);
		}
		orderBook.setAsksList(asksList);
		
		JSONArray arrBids = jsonData.getJSONArray("bids");
		List<Bids> bidsList = new ArrayList<Bids>();
		for(Object object : arrBids) {
			JSONObject obj = (JSONObject)object;
			Bids bids = new Bids();
			bids.setQuantity(obj.getString("quantity"));
			bids.setPrice(obj.getString("price"));
			bidsList.add(bids);
		}
		orderBook.setBidsList(bidsList);
		
		System.out.println(orderBook.toString());
		
		return orderBook;
	}
	
	public RecentTransactions getRecentTransactions(HashMap<String, String> paramMap) {
		String result = API.callApi(RECENT_URL, paramMap);
		JSONObject jsonObject = new JSONObject(result);
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		
		List<Transaction> transactionList = new ArrayList<Transaction>();
		for(Object object : jsonArray) {
			JSONObject obj = (JSONObject)object;
			Transaction transaction = new Transaction();
			transaction.setTransactionDate(obj.getString("transaction_date"));
			transaction.setType(obj.getString("type"));
			transaction.setUnitsTraded(obj.getString("units_traded"));
			transaction.setPrice(obj.getString("price"));
			transaction.setTotal(obj.getString("total"));
			transactionList.add(transaction);
		}
		
		RecentTransactions recentTransactions = new RecentTransactions();
		recentTransactions.setTransactionList(transactionList);
		
		System.out.println(recentTransactions.toString());
		
		return recentTransactions;
	}
	
	public static void main(String[] args) {
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		
		PublicController publicController = new PublicController();
		publicController.getTicker(paramMap);
		publicController.getOrderBook(paramMap);
		publicController.getRecentTransactions(paramMap);
	}
}
