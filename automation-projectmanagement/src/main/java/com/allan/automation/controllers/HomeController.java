package com.allan.automation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.allan.automation.dao.AutoProjectRepository;
import com.allan.automation.dao.EmployeeRepository;
import com.allan.automation.dto.ChartData;
import com.allan.automation.dto.EmployeeAutoProject;
import com.allan.automation.entites.AutoProject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HomeController {

	@Autowired
	private AutoProjectRepository autoProjectRepo;

	@Autowired
	private EmployeeRepository employeeRepo;

	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		List<AutoProject> autoProjects = autoProjectRepo.findAll();
		model.addAttribute("autoProjects", autoProjects);

		List<ChartData> chartDataList = autoProjectRepo.findAutoProjectStatus();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(chartDataList);
		model.addAttribute("chartDataList", jsonString);

		List<EmployeeAutoProject> employees = employeeRepo.findAllEmployeeAutoProject();
		model.addAttribute("employees", employees);
		return "main/home";
	}
}
