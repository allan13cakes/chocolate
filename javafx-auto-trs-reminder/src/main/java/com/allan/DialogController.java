package com.allan;

import com.allan.model.AutoData;
import com.allan.model.AutoSample;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class DialogController {

	@FXML
	private TextField nameField;

	@FXML
	private ToggleGroup browserTypeGroup;
	@FXML
	private RadioButton chromeRadioButton;
	@FXML
	private RadioButton ieRadioButton;

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

	public void initialize() {
		templateChoiceBox.getItems().add("template 1");
		templateChoiceBox.getItems().add("template 2");
	}

	public AutoSample processResults() {
		String name = nameField.getText().trim();
		String browserType = browserTypeGroup.getSelectedToggle().getUserData().toString().trim();
		String gocs = gocsArea.getText().trim();
		String template = templateChoiceBox.getSelectionModel().getSelectedItem().trim();
		String ignores = ignoresField.getText().trim();
		Boolean isOpenBrowser = isOpenBrowserGroup.getSelectedToggle().getUserData().equals("yes");
		AutoSample autoSample = new AutoSample();
		autoSample.setName(name);
		autoSample.setBrowserType(browserType);
		autoSample.setGocs(gocs);
		autoSample.setTemplate(template);
		autoSample.setIgnores(ignores);
		autoSample.setOpenBrowser(isOpenBrowser);
		AutoData.getInstance().addAutoSample(autoSample);
		return autoSample;
	}

}
