package com.bithumb;

import java.io.IOException;

import com.bithumb.controller.CurrencyController;
import com.bithumb.model.Currency;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private ObservableList<Currency> currencyData = FXCollections.observableArrayList();
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	public MainApp() {
		getCurrencyData().add(new Currency("BTC", "비트코인"));
		getCurrencyData().add(new Currency("ETH", "이더리움"));
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Bithumb");
        
		initRootLayout();
		
		showTicker();
	}
	
	public void initRootLayout() {
        try {
            // FXML 파일에서 상위 레이아웃을 가져온다.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // 상위 레이아웃을 포함하는 scene을 보여준다.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showTicker() {
        try {
            // 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Ticker.fxml"));
            AnchorPane ticker = (AnchorPane) loader.load();

            // 
            rootLayout.setCenter(ticker);
            
            CurrencyController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public ObservableList<Currency> getCurrencyData() {
		return currencyData;
	}

	public void setCurrencyData(ObservableList<Currency> currencyData) {
		this.currencyData = currencyData;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
