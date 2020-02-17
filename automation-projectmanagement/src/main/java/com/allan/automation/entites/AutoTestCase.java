package com.allan.automation.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AutoTestCase {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_test_case_seq")
	private long autoTestCaseId;
	private String jiraKey;
	private String summary;
	private String stage;

	public long getAutoTestCaseId() {
		return autoTestCaseId;
	}

	public void setAutoTestCaseId(long autoTestCaseId) {
		this.autoTestCaseId = autoTestCaseId;
	}

	public String getJiraKey() {
		return jiraKey;
	}

	public void setJiraKey(String jiraKey) {
		this.jiraKey = jiraKey;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

}
