package com.allan.automation.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.allan.automation.entites.AutoTestCase;

public interface AutoTestCaseRepository extends CrudRepository<AutoTestCase, Long> {
	public List<AutoTestCase> findAll();
}
