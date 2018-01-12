package com.bithumb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bithumb.api.Api_Client;
import com.bithumb.model.Asks;
import com.bithumb.model.Bids;
import com.bithumb.model.OrderBook;
import com.bithumb.model.Ticker;

public class Main {
	
	private static final Api_Client API = new Api_Client("api connect key", "api secret key");
	
	public JsonNode getDataNode(String order, HashMap<String, String> paramMap) {
		String result = API.callApi(order, paramMap);
	    ObjectMapper objectMapper = new ObjectMapper();
	    JsonNode rootNode = null;
	    JsonNode dataNode = null;
	    
	    try {
	    	rootNode = objectMapper.readTree(result);
	    	dataNode = rootNode.path("data");
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return dataNode;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getDataMap(String order, HashMap<String, String> paramMap) {
		String result = API.callApi(order, paramMap);
	    ObjectMapper objectMapper = new ObjectMapper();
	    JsonNode rootNode = null;
	    HashMap<String, String> dataMap = new HashMap<String, String>();
	    
	    try {
	    	rootNode = objectMapper.readTree(result);
	    	JsonNode dataNode = rootNode.path("data");
	    	dataMap = new ObjectMapper().readValue(dataNode, HashMap.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return dataMap;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getNodeToMap(JsonNode jsonNode){
		HashMap<String, String> dataMap = new HashMap<String, String>();
		try {
			dataMap = new ObjectMapper().readValue(jsonNode, HashMap.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}
	
	
	public void setTicker() {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		HashMap<String, String> dataMap = getDataMap("/public/ticker/BTC", paramMap);
		
		Ticker ticker = new Ticker();
	    ticker.setOpeningPrice(dataMap.get("opening_price"));
	    ticker.setClosingPrice(dataMap.get("closing_price"));
	    ticker.setMinPrice(dataMap.get("min_price"));
	    ticker.setMaxPrice(dataMap.get("max_price"));
	    ticker.setAveragePrice(dataMap.get("average_price"));
	    ticker.setUnitsTraded(dataMap.get("units_traded"));
	    ticker.setVolume1Day(dataMap.get("volume_1day"));
	    ticker.setVolume7Day(dataMap.get("volume_7day"));
	    ticker.setBuyPrice(dataMap.get("buy_price"));
	    ticker.setSellPrice(dataMap.get("sell_price"));
	    ticker.setDate(dataMap.get("date"));
	    
	    System.out.println(ticker.toString());
	}
	
	public void setOrderBook() {
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		HashMap<String, String> dataMap = getDataMap("/public/orderbook/BTC", paramMap);
		
		OrderBook orderBook = new OrderBook();
    	orderBook.setOrderCurrency(dataMap.get("order_currency"));
    	orderBook.setPaymentCurrency(dataMap.get("payment_currency"));
    	orderBook.setTimestamp(dataMap.get("timestamp"));
    	
    	JsonNode dataNode = getDataNode("/public/orderbook/BTC", paramMap);
    	
    	if( dataNode.path("asks").isArray() ){
    		Iterator<JsonNode> iterator = dataNode.path("asks").iterator();
    		List<Asks> asksList = new ArrayList<Asks>();
    		while(iterator.hasNext()){
    			HashMap<String, String> asksMap = getNodeToMap(iterator.next());
    			
    			Asks asks = new Asks();
//    			asks.setQuantity(asksMap.get("quantity"));
//    			asks.setPrice(asksMap.get("price"));
    			asksList.add(asks);
    		}
    		orderBook.setAsksList(asksList);
    	}
    	
    	if( dataNode.path("bids").isArray() ){
    		Iterator<JsonNode> iterator = dataNode.path("bids").iterator();
    		List<Bids> bidsList = new ArrayList<Bids>();
    		while(iterator.hasNext()){
    			HashMap<String, String> bidsMap = getNodeToMap(iterator.next());
    			Bids bids = new Bids();
//    			bids.setQuantity(bidsMap.get("quantity"));
//    			bids.setPrice(bidsMap.get("price"));
    			bidsList.add(bids);
    		}
    		orderBook.setBidsList(bidsList);
    	}
    	
    	System.out.println(orderBook.toString());
		
	}
	
	public JSONObject getResultJson(String path, HashMap<String, String> paramMap) {
		String result = API.callApi("/public/orderbook/BTC", paramMap);
		JSONObject jsonObject = new JSONObject(result);
		return jsonObject;
	}
	
	public static void main(String args[]) {
    	
    	HashMap<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("order_currency", "BTC");
    	paramMap.put("payment_currency", "KRW");
		
//    	Main main = new Main();
//    	main.setOrderBook();
//    	main.setTicker();
	
		    
//		String result = API.callApi("/public/orderbook/BTC", paramMap);
//	    ObjectMapper objectMapper = new ObjectMapper();
//	    JsonNode rootNode = null;
//	    
//	    try {
//	    	rootNode = objectMapper.readTree(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//			
//		System.out.println(rootNode);
//		System.out.println(rootNode.getFieldNames());
//		
//		Iterator<Entry<String, JsonNode>> iterator = rootNode.getFields();
//		while(iterator.hasNext()) {
//			
//			Entry<String, JsonNode> entry = iterator.next();
//			
//			String key = entry.getKey();
//			
//			System.out.println(key);
//			System.out.println(entry.getValue());
//			
//			
//			
//		}
		
		String result = API.callApi("/public/orderbook/BTC", paramMap);
		JSONObject jsonObject = new JSONObject(result);
		
		JSONObject jsonData = jsonObject.getJSONObject("data");
		
		OrderBook orderBook = new OrderBook();
    	orderBook.setOrderCurrency(jsonData.getString("order_currency"));
    	orderBook.setPaymentCurrency(jsonData.getString("payment_currency"));
    	orderBook.setTimestamp(jsonData.getString("timestamp"));
		
		JSONArray jsonArray = jsonData.getJSONArray("asks");
		List<Asks> asksList = new ArrayList<Asks>();
		for(Object object : jsonArray) {
			JSONObject obj = (JSONObject)object;
			Asks asks = new Asks();
			asks.setQuantity(obj.getString("quantity"));
			asks.setPrice(obj.getString("price"));
			asksList.add(asks);
		}
		orderBook.setAsksList(asksList);
		
		System.out.println(orderBook.toString());
		
    }
}

