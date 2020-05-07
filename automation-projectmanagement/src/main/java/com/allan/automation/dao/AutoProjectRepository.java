package com.allan.automation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.allan.automation.dto.ChartData;
import com.allan.automation.entites.AutoProject;

public interface AutoProjectRepository
		extends CrudRepository<AutoProject, Long>, PagingAndSortingRepository<AutoProject, Long> {

	public List<AutoProject> findAll();

	@Query(nativeQuery = true, value = "select stage as label, count(*) as value from auto_project group by stage")
	public List<ChartData> findAutoProjectStatus();
}
