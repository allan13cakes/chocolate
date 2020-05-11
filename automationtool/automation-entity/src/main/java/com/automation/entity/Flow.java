package com.automation.entity;

import java.util.List;

public class Flow {
	private long id;
	private String name;
	private List<FlowAction> flowActions;

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

	public List<FlowAction> getFlowActions() {
		return flowActions;
	}

	public void setFlowActions(List<FlowAction> flowActions) {
		this.flowActions = flowActions;
	}

	@Override
	public String toString() {
		return "Flow [id=" + id + ", name=" + name + ", flowActions=" + flowActions + "]";
	}

}
