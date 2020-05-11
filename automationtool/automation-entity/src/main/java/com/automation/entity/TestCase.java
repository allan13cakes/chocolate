package com.automation.entity;

import java.util.List;

public class TestCase {
	private long id;
	private String jiraKey;
	private String summary;
	private String component;
	private String subComponent;

	private List<TestStep> testSteps;
	private List<TestData> testDatas;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getSubComponent() {
		return subComponent;
	}

	public void setSubComponent(String subComponent) {
		this.subComponent = subComponent;
	}

	public List<TestStep> getTestSteps() {
		return testSteps;
	}

	public void setTestSteps(List<TestStep> testSteps) {
		this.testSteps = testSteps;
	}

	public List<TestData> getTestDatas() {
		return testDatas;
	}

	public void setTestDatas(List<TestData> testDatas) {
		this.testDatas = testDatas;
	}

	@Override
	public String toString() {
		return "TestCase [id=" + id + ", jiraKey=" + jiraKey + ", summary=" + summary + ", component=" + component
				+ ", subComponent=" + subComponent + ", testSteps=" + testSteps + ", testDatas=" + testDatas + "]";
	}

}
