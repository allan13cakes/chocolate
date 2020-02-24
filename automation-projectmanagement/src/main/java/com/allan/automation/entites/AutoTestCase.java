package com.allan.automation.entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class AutoTestCase {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_test_case_seq")
	private long autoTestCaseId;
	private String jiraKey;
	private String summary;
	private String stage;

	@OneToMany(mappedBy = "autoTestCase", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private Set<AutoTestStep> autoTestSteps;

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

	public Set<AutoTestStep> getAutoTestSteps() {
		if (autoTestSteps == null) {
			autoTestSteps = new HashSet<>();
		}
		return autoTestSteps;
	}

	public void setAutoTestSteps(Set<AutoTestStep> autoTestSteps) {
		this.autoTestSteps = autoTestSteps;
	}

}
