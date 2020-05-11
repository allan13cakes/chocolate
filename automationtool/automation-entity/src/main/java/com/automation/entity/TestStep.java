package com.automation.entity;

public class TestStep {
	private long id;
	private String stepOrder;
	private String description;
	private Flow flow;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(String stepOrder) {
		this.stepOrder = stepOrder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}

	@Override
	public String toString() {
		return "TestStep [id=" + id + ", stepOrder=" + stepOrder + ", description=" + description + ", flow=" + flow
				+ "]";
	}

}
