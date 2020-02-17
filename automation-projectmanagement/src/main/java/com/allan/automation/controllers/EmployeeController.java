package com.allan.automation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.allan.automation.dao.EmployeeRepository;
import com.allan.automation.entites.Employee;

@Controller
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepo;

	@GetMapping
	public String displayEmployee(Model model) {
		List<Employee> employees = employeeRepo.findAll();
		model.addAttribute("employees", employees);
		return "employee/list-employee";
	}

	@GetMapping("/new")
	private String displayEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employee/new-employee";
	}

	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model) {
		employeeRepo.save(employee);
		return "redirect:/employee";
	}

}
