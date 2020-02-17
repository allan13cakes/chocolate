package com.allan.automation.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AutoTestData {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_test_data_seq")
	private long autoTestDataId;
	private String name;
	private String value;

	public long getAutoTestDataId() {
		return autoTestDataId;
	}

	public void setAutoTestDataId(long autoTestDataId) {
		this.autoTestDataId = autoTestDataId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
