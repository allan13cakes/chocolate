package com.automation.entity;

import java.util.List;

public class TestSet {
	private long id;
	private String name;
	private List<TestCase> testCases;
	private List<TestCaseResult> testCaseResults;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}

	public List<TestCaseResult> getTestCaseResults() {
		return testCaseResults;
	}

	public void setTestCaseResults(List<TestCaseResult> testCaseResults) {
		this.testCaseResults = testCaseResults;
	}

}
