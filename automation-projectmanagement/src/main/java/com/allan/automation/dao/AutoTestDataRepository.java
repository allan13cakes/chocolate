package com.allan.automation.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.allan.automation.entites.AutoTestData;

public interface AutoTestDataRepository extends CrudRepository<AutoTestData, Long> {
	public List<AutoTestData> findAll();
}
