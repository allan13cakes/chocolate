package com.allan.model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AutoData {
	private static AutoData instance = new AutoData();
	private static String filename = "settings.xml";

	private ArrayList<AutoSample> autoSamples;
	private ObservableList<AutoSample> obList = FXCollections.observableArrayList();

	public static AutoData getInstance() {
		return instance;
	}

	public ObservableList<AutoSample> getAutoSamples() {
		return obList;
	}

	public void addAutoSample(AutoSample autoSample) {
		autoSamples.add(autoSample);
		obList = FXCollections.observableArrayList(autoSamples);
	}

	@SuppressWarnings("unchecked")
	public void loadAutoSamples() {
		autoSamples = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(filename); XMLDecoder decoder = new XMLDecoder(fis)) {
			autoSamples = (ArrayList<AutoSample>) decoder.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!autoSamples.isEmpty()) {
			obList = FXCollections.observableArrayList(autoSamples);
		}

	}

	public void store() {
		System.out.println("store data");
		try (FileOutputStream fos = new FileOutputStream(filename); XMLEncoder encoder = new XMLEncoder(fos)) {
			encoder.setExceptionListener((Exception e) -> {
				e.printStackTrace();
			});
			encoder.writeObject(autoSamples);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
