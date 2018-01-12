package com.bithumb.controller;

import com.bithumb.MainApp;
import com.bithumb.model.Currency;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CurrencyController {
	
	@FXML
    private TableView<Currency> currencyTable;
    @FXML
    private TableColumn<Currency, String> currencyCodeColumn;
    @FXML
    private TableColumn<Currency, String> currencyNameColumn;
    
    @FXML
    private Label currencyCodeLabel;
    @FXML
    private Label currencyNameLabel;
    
    private MainApp mainApp;
    
    public CurrencyController() {
		// TODO Auto-generated constructor stub
	}
    
    @FXML
    private void initialize() {
    	currencyCodeColumn.setCellValueFactory(cellData -> cellData.getValue().currencyCodeProperty());
    	currencyNameColumn.setCellValueFactory(cellData -> cellData.getValue().currencyNameProperty());
    	
    	currencyTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
    }
    
    private void showPersonDetails(Currency currency) {
        if (currency != null) {
            
        }
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        currencyTable.setItems(mainApp.getCurrencyData());
    }
}
