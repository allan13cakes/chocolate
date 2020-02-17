package com.allan.automation.views;

import java.util.List;

import com.allan.automation.entites.AutoFlow;

public class AutoFlowForm {
	private AutoFlow autoFlow;
	private List<Long> selectedAutoActionId;

	public AutoFlow getAutoFlow() {
		return autoFlow;
	}

	public void setAutoFlow(AutoFlow autoFlow) {
		this.autoFlow = autoFlow;
	}

	public List<Long> getSelectedAutoActionId() {
		return selectedAutoActionId;
	}

	public void setSelectedAutoActionId(List<Long> selectedAutoActionId) {
		this.selectedAutoActionId = selectedAutoActionId;
	}

}
