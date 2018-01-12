package com.bithumb.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Currency{
	
	private final StringProperty currencyCode;
	private final StringProperty currencyName;
	
	public Currency() {
		this(null, null);
	}
	
	public Currency(String currencyCode, String currencyName) {
		this.currencyCode = new SimpleStringProperty(currencyCode);
		this.currencyName = new SimpleStringProperty(currencyName);
	}
	
	public String getCurrencyCode() {
        return currencyCode.get();
    }
	
	public void setCurrencyCode(String currencyCode) {
        this.currencyCode.set(currencyCode);
    }

	public StringProperty currencyCodeProperty() {
		return currencyCode;
	}
	
	public String getCurrencyName() {
        return currencyName.get();
    }
	
	public void setCurrencyName(String currencyName) {
        this.currencyName.set(currencyName);
    }

	public StringProperty currencyNameProperty() {
		return currencyName;
	}

}
