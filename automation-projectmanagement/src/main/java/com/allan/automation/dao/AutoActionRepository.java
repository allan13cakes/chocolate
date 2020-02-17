package com.allan.automation.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.allan.automation.entites.AutoAction;

public interface AutoActionRepository extends CrudRepository<AutoAction, Long> {
	public List<AutoAction> findAll();
}
