package com.automation.entity;

import java.util.List;

public class TestCaseResult {
	private long id;
	private String status;
	private TestCase testCase;
	private List<TestStepResult> testStepResults;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TestCase getTestCase() {
		return testCase;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}

	public List<TestStepResult> getTestStepResults() {
		return testStepResults;
	}

	public void setTestStepResults(List<TestStepResult> testStepResults) {
		this.testStepResults = testStepResults;
	}

}
