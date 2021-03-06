package com.allan.automation.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class AutoTestStep {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_test_step_seq")
	private long autoTestStepId;
	private String description;
	private int stepOrder;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auto_test_case_id")
	private AutoTestCase autoTestCase;

	@ManyToOne
	@JoinColumn(name = "auto_flow_id")
	private AutoFlow autoFlow;

	public long getAutoTestStepId() {
		return autoTestStepId;
	}

	public void setAutoTestStepId(long autoTestStepId) {
		this.autoTestStepId = autoTestStepId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(int stepOrder) {
		this.stepOrder = stepOrder;
	}

	public AutoTestCase getAutoTestCase() {
		return autoTestCase;
	}

	public void setAutoTestCase(AutoTestCase autoTestCase) {
		this.autoTestCase = autoTestCase;
	}

	public AutoFlow getAutoFlow() {
		return autoFlow;
	}

	public void setAutoFlow(AutoFlow autoFlow) {
		this.autoFlow = autoFlow;
	}

}
