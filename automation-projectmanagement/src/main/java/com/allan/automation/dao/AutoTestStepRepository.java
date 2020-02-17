package com.allan.automation.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.allan.automation.entites.AutoTestStep;

public interface AutoTestStepRepository extends CrudRepository<AutoTestStep, Long> {
	public List<AutoTestStep> findAll();
}
