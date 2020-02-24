package com.allan.automation.views;

import java.util.List;

import com.allan.automation.entites.AutoTestCase;

public class AutoTestCaseForm {
	private AutoTestCase autoTestCase;
	private List<String> selectedActionIds;

	public AutoTestCase getAutoTestCase() {
		return autoTestCase;
	}

	public void setAutoTestCase(AutoTestCase autoTestCase) {
		this.autoTestCase = autoTestCase;
	}

	public List<String> getSelectedActionIds() {
		return selectedActionIds;
	}

	public void setSelectedActionIds(List<String> selectedActionIds) {
		this.selectedActionIds = selectedActionIds;
	}

}
