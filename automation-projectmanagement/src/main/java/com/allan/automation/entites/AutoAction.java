package com.allan.automation.entites;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class AutoAction {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_action_seq")
	private long autoActionId;
	private String name;
	private String description;
	private String parameters;

	@OneToMany(mappedBy = "autoAction")
	private Set<AutoActionFlow> autoActionFlows;

	public AutoAction() {
		super();
	}

	public AutoAction(String name, String description, String parameters) {
		super();
		this.name = name;
		this.description = description;
		this.parameters = parameters;
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

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public Set<AutoActionFlow> getAutoActionFlows() {
		return autoActionFlows;
	}

	public void setAutoActionFlows(Set<AutoActionFlow> autoActionFlows) {
		this.autoActionFlows = autoActionFlows;
	}

	public List<String> getParsedParameters() {
		ObjectMapper mapper = new ObjectMapper();
		List<String> listParameter = new ArrayList<>();
		try {
			listParameter = mapper.readValue(this.parameters, new TypeReference<List<String>>() {
			});
		} catch (Exception e) {
			listParameter.add(this.parameters);
		}
		return listParameter;
	}

}
