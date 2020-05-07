package com.allan.automation.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.allan.automation.entites.AutoFlow;

public interface AutoFlowRepository extends CrudRepository<AutoFlow, Long>, PagingAndSortingRepository<AutoFlow, Long> {
	public List<AutoFlow> findAll();
}
