package com.allan.automation.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.allan.automation.entites.AutoTestCase;

public interface AutoTestCaseRepository extends CrudRepository<AutoTestCase, Long>,PagingAndSortingRepository<AutoTestCase,Long> {
	public List<AutoTestCase> findAll();
}
