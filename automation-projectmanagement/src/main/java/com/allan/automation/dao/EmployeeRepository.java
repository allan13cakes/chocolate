package com.allan.automation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.allan.automation.dto.EmployeeAutoProject;
import com.allan.automation.entites.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	public List<Employee> findAll();

	@Query(nativeQuery = true, value = "select e.name,e.email, count(pe.employee_id) as projectCnt from employee e left join auto_project_employee pe on e.employee_id = pe.employee_id group by e.name,e.email order by 3 desc")
	public List<EmployeeAutoProject> findAllEmployeeAutoProject();
}
