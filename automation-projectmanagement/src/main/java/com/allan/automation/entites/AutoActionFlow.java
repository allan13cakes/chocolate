package com.allan.automation.entites;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AutoActionFlow {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_action_flow_seq")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auto_flow_id")
	private AutoFlow autoFlow;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auto_action_id")
	private AutoAction autoAction;

	private int actionOrder;

	public AutoActionFlow() {

	}

	public AutoActionFlow(AutoFlow autoFlow, AutoAction autoAction) {
		this.autoFlow = autoFlow;
		this.autoAction = autoAction;
	}

	public AutoAction getAutoAction() {
		return autoAction;
	}

	public void setAutoAction(AutoAction autoAction) {
		this.autoAction = autoAction;
	}

	public AutoFlow getAutoFlow() {
		return autoFlow;
	}

	public void setAutoFlow(AutoFlow autoFlow) {
		this.autoFlow = autoFlow;
	}

	public int getActionOrder() {
		return actionOrder;
	}

	public void setActionOrder(int actionOrder) {
		this.actionOrder = actionOrder;
	}

}
