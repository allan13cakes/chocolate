package com.allan;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import com.allan.model.AutoData;
import com.allan.model.AutoSample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;

public class Controller {

	private AutoSample selectedSample = null;

	@FXML
	private BorderPane mainBorderPane;
	@FXML
	private ListView<AutoSample> sampleListView;

	@FXML
	private TextField nameField;
	@FXML
	private RadioButton chromeRadioButton;
	@FXML
	private RadioButton ieRadioButton;
	@FXML
	private ToggleGroup browserTypeGroup;

	@FXML
	private RadioButton yesRadioButton;
	@FXML
	private RadioButton noRadionButton;
	@FXML
	private ToggleGroup isOpenBrowserGroup;

	@FXML
	private TextArea gocsArea;
	@FXML
	private ChoiceBox<String> templateChoiceBox;
	@FXML
	private TextField ignoresField;

	@FXML
	private TextFlow consoleTextFlow;

	public void initialize() throws FileNotFoundException, IOException {
		templateChoiceBox.getItems().add("template 1");
		templateChoiceBox.getItems().add("template 2");

		sampleListView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			selectedSample = newValue;
			if (selectedSample != null) {
				// update main page
				nameField.setText(selectedSample.getName());
				if (selectedSample.getBrowserType().equals("ie")) {
					ieRadioButton.setSelected(true);
				} else {
					chromeRadioButton.setSelected(true);
				}
				gocsArea.setText(selectedSample.getGocs());
				templateChoiceBox.setValue(selectedSample.getTemplate());
				ignoresField.setText(selectedSample.getIgnores());
			}
		});

		sampleListView.setItems(AutoData.getInstance().getAutoSamples());
		sampleListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		sampleListView.getSelectionModel().selectFirst();

		sampleListView.setCellFactory(new Callback<ListView<AutoSample>, ListCell<AutoSample>>() {
			@Override
			public ListCell<AutoSample> call(ListView<AutoSample> param) {
				ListCell<AutoSample> cell = new ListCell<AutoSample>() {

					@Override
					protected void updateItem(AutoSample item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setText(null);
						} else {
							setText(item.getName());

						}
					}
				};
				return cell;
			}
		});

	}

	@FXML
	public void showNewItemDialog() {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(mainBorderPane.getScene().getWindow());
		dialog.setTitle("Add New template");
		dialog.setHeaderText("Use this dialog to create a new template");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getClassLoader().getResource("autoSampleDialog.fxml"));

		try {
			dialog.getDialogPane().setContent(fxmlLoader.load());
		} catch (IOException e) {
			System.out.println("Couldn't load the dialog");
			e.printStackTrace();
			return;
		}

		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

		Optional<ButtonType> result = dialog.showAndWait();

		if (result.isPresent() && result.get() == ButtonType.OK) {
			DialogController controller = fxmlLoader.getController();
			AutoSample autoSample = controller.processResults();
			sampleListView.setItems(AutoData.getInstance().getAutoSamples());
			sampleListView.getSelectionModel().select(autoSample);
		}
	}

	@FXML
	public void run() {
		Text text1 = new Text("run start...\n");
		consoleTextFlow.getChildren().add(text1);
	}

}
