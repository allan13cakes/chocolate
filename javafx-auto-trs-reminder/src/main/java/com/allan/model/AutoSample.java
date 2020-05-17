package com.allan.model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class AutoSample {
	private static final String PATH = "settings.xml";
	private String name;
	private String content;
	private String emailSubject;
	private String emailFrom;
	private String emailTo;
	private String emailCc;

	public AutoSample() {
		super();
	}

	public AutoSample(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getEmailCc() {
		return emailCc;
	}

	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}

	@SuppressWarnings("unchecked")
	public static List<AutoSample> load() throws FileNotFoundException, IOException {
		List<AutoSample> list;
		try (FileInputStream fis = new FileInputStream(PATH); XMLDecoder decoder = new XMLDecoder(fis)) {
			list = (List<AutoSample>) decoder.readObject();
		}
		return list;
	}

	public static void store(List<AutoSample> list) throws FileNotFoundException, IOException {
		try (FileOutputStream fos = new FileOutputStream(PATH); XMLEncoder encoder = new XMLEncoder(fos)) {
			encoder.setExceptionListener((Exception e) -> {
				e.printStackTrace();
			});
			encoder.writeObject(list);
		}
	}

}
