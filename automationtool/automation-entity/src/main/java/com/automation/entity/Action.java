package com.automation.entity;

import java.util.List;

public class Action {
	private long id;
	private String name;
	private List<Parameter> parameters;

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

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "Action [id=" + id + ", name=" + name + ", parameters=" + parameters + "]";
	}

}
