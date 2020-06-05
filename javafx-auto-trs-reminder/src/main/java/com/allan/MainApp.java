package com.allan;

import com.allan.model.AutoData;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainApp extends Application {
	private static String DEFAULT_TITLE = "Auto_TRS_Reminder";

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainApp.fxml"));
		primaryStage.setTitle(DEFAULT_TITLE);
		Scene scene = new Scene(root, 900, 500);
		primaryStage.setScene(scene);
		primaryStage.setMinWidth(1000);
		primaryStage.setMinHeight(600);

		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setWidth(screenBounds.getWidth() * 0.75);
		primaryStage.setHeight(screenBounds.getHeight() * 0.75);

		primaryStage.show();
	}

	public void stop() {
		AutoData.getInstance().store();

	}

	public void init() {
		AutoData.getInstance().loadAutoSamples();
	}

}
