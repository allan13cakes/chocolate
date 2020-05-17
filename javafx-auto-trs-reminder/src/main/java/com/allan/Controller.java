package com.allan;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.allan.model.AutoSample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;

public class Controller {

	private List<AutoSample> autoSampleList = new ArrayList<>();
	private AutoSample selectedSample = null;

	@FXML
	private TextField searchBox;
	@FXML
	private ListView<AutoSample> sampleListView;
	@FXML
	private TextField nameField;
	@FXML
	private TextField emailSubjectField;
	@FXML
	private TextField emailFromField;
	@FXML
	private TextField emailToField;
	@FXML
	private TextField emailCcField;
	@FXML
	private TextArea contentTextArea;
	@FXML
	private TextArea consoleTextArea;

	public void initialize() throws FileNotFoundException, IOException {
		GridPane.setMargin(searchBox, new Insets(5, 0, 0, 0));
		GridPane.setVgrow(sampleListView, Priority.ALWAYS);
		sampleListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		searchBox.textProperty().addListener((obs, oldValue, newValue) -> {
			updateListView();
		});

		sampleListView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			selectedSample = newValue;
			// update main page
			nameField.setText(selectedSample.getName());
			emailSubjectField.setText(selectedSample.getEmailSubject());
			emailFromField.setText(selectedSample.getEmailFrom());
			emailToField.setText(selectedSample.getEmailTo());
			emailCcField.setText(selectedSample.getEmailCc());
			contentTextArea.setText(selectedSample.getContent());
		});

		autoSampleList = AutoSample.load();
		if (!autoSampleList.isEmpty()) {
			updateListView();
			sampleListView.getSelectionModel().selectFirst();
			selectedSample = autoSampleList.get(0);
		}

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

	private void updateListView() {
		ObservableList<AutoSample> samples = FXCollections.observableArrayList();
		autoSampleList.forEach(item -> samples.add(item));

		Predicate<AutoSample> filter = new Predicate<AutoSample>() {
			@Override
			public boolean test(AutoSample autoSample) {
				if (searchBox.getText() != null) {
					return autoSample.getName().contains(searchBox.getText());
				}
				return true;

			}
		};

		FilteredList<AutoSample> filteredList = new FilteredList<AutoSample>(samples, filter);
		sampleListView.setItems(filteredList);

	}

	@FXML
	public void run() {
		consoleTextArea.appendText("run start...");
	}

}
