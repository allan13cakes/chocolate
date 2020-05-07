package com.allan.automation.entites;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class AutoProject extends BaseDomain{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_project_seq")
	private long autoProjectId;
	private String name;
	private String stage;
	private String description;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "auto_project_employee", joinColumns = @JoinColumn(name = "auto_project_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
	private List<Employee> employees;

	public long getAutoProjectId() {
		return autoProjectId;
	}

	public void setAutoProjectId(long autoProjectId) {
		this.autoProjectId = autoProjectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	

	@Override
	public String toString() {
		return "AutoProject [autoProjectId=" + autoProjectId + ", name=" + name + ", stage=" + stage + ", description="
				+ description + ", employees=" + employees + "]";
	}

}
