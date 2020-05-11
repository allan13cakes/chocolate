package com.allan.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class MethodFinderController {
	@FXML
	private TreeView<String> locationTreeView;

	public void initialize(URL location, ResourceBundle resources) {
		loadTreeItems(new TreeItem<String>("Root Node"));
	}

	public void loadTreeItems(TreeItem<String> root) {
		locationTreeView.setRoot(root);
	}
}
