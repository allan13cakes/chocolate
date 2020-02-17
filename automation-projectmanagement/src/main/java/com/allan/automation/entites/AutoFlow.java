package com.allan.automation.entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class AutoFlow {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_action_flow_seq")
	private long autoFlowId;

	private String name;

	@OneToMany(mappedBy = "autoFlow", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private Set<AutoActionFlow> autoActionFlows;

	public AutoFlow() {
		super();
	}

	public AutoFlow(String name) {
		super();
		this.name = name;
		this.autoActionFlows = new HashSet<>();
	}

	public long getAutoFlowId() {
		return autoFlowId;
	}

	public void setAutoFlowId(long autoFlowId) {
		this.autoFlowId = autoFlowId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AutoActionFlow> getAutoActionFlows() {
		if(this.autoActionFlows==null) {
			this.autoActionFlows = new HashSet<>();
		}
		return autoActionFlows;
	}

	public void setAutoActionFlows(Set<AutoActionFlow> autoActionFlows) {
		this.autoActionFlows = autoActionFlows;
	}

}
