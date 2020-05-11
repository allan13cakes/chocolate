package com.automation.entity;

public class FlowAction {
	private long id;
	private int actionOrder;
	private Flow flow;
	private Action action;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getActionOrder() {
		return actionOrder;
	}

	public void setActionOrder(int actionOrder) {
		this.actionOrder = actionOrder;
	}

	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "FlowAction [id=" + id + ", actionOrder=" + actionOrder + ", flow=" + flow + ", action=" + action + "]";
	}

}
