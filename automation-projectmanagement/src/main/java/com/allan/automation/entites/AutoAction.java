package com.allan.automation.entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AutoAction {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_action_seq")
	private long autoActionId;
	private String name;
	private String description;
	@OneToMany(mappedBy = "autoAction")
	private Set<AutoParameter> autoParameters;
	@JsonIgnore
	@OneToMany(mappedBy = "autoAction")
	private Set<AutoActionFlow> autoActionFlows;

	public AutoAction() {
		super();
	}

	public AutoAction(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public long getAutoActionId() {
		return autoActionId;
	}

	public void setAutoActionId(long autoActionId) {
		this.autoActionId = autoActionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<AutoActionFlow> getAutoActionFlows() {
		return autoActionFlows;
	}

	public void setAutoActionFlows(Set<AutoActionFlow> autoActionFlows) {
		this.autoActionFlows = autoActionFlows;
	}

	public Set<AutoParameter> getAutoParameters() {
		if (autoParameters == null) {
			autoParameters = new HashSet<>();
		}
		return autoParameters;
	}

	public void setAutoParameters(Set<AutoParameter> autoParameters) {
		this.autoParameters = autoParameters;
	}

}
