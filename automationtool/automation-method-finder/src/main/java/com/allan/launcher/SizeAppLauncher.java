package com.allan.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SizeAppLauncher extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			final Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("SizeApp.fxml"));
			final Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Size Example");

//			stage.setMinHeight(250);
//			stage.setMinWidth(500);
//			
//			stage.setMaxHeight(500);
//			stage.setMaxWidth(1000);

			stage.minWidthProperty().bind(scene.heightProperty().multiply(3));
			stage.minHeightProperty().bind(scene.widthProperty().divide(2));

			stage.show();
		} catch (Exception e) {
			System.out.print(e);
		}
	}
}