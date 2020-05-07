package com.allan.automation.entites;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AutoParameter {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_parameter_seq")
	private long autoParameterId;
	private String name;
	private String value;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auto_test_step_id")
	private AutoTestStep autoTestStep;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auto_flow_id")
	private AutoFlow autoFlow;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auto_action_id")
	private AutoAction autoAction;

	public AutoParameter() {
	}

	public AutoParameter(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public AutoTestStep getAutoTestStep() {
		return autoTestStep;
	}

	public void setAutoTestStep(AutoTestStep autoTestStep) {
		this.autoTestStep = autoTestStep;
	}

	public AutoFlow getAutoFlow() {
		return autoFlow;
	}

	public void setAutoFlow(AutoFlow autoFlow) {
		this.autoFlow = autoFlow;
	}

	public AutoAction getAutoAction() {
		return autoAction;
	}

	public void setAutoAction(AutoAction autoAction) {
		this.autoAction = autoAction;
	}

	public long getAutoActionId() {
		return autoAction != null ? autoAction.getAutoActionId() : -1l;
	}

	public long getAutoFlowId() {
		return autoFlow != null ? autoFlow.getAutoFlowId() : -1l;
	}

	public long getAutoTestStepId() {
		return autoTestStep != null ? autoTestStep.getAutoTestStepId() : -1l;
	}

}
