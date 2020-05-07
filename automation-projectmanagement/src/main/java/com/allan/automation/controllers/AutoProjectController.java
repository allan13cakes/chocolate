package com.allan.automation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.allan.automation.dao.AutoProjectRepository;
import com.allan.automation.dao.EmployeeRepository;
import com.allan.automation.entites.AutoProject;
import com.allan.automation.entites.Employee;

@Controller
@RequestMapping("autoprojects")
public class AutoProjectController {
	@Autowired
	private AutoProjectRepository autoProjectRepo;

	@Autowired
	private EmployeeRepository employeeRepo;

	@GetMapping
	public String displayAutoProjects(Model model) {
		List<AutoProject> autoProjects = autoProjectRepo.findAll();
		model.addAttribute("autoProjects", autoProjects);
		return "autoprojects/list-autoproject";
	}

	@GetMapping("/new")
	private String displayCreateForm(Model model) {
		AutoProject autoProject = new AutoProject();
		model.addAttribute("autoProject", autoProject);

		List<Employee> employees = employeeRepo.findAll();
		model.addAttribute("allEmployees", employees);
		return "autoprojects/new-autoproject";
	}

	@PostMapping("/save")
	public String createAutoProject(AutoProject autoProject, Model model) {
		autoProjectRepo.save(autoProject);
		return "redirect:/autoprojects";
	}

	@GetMapping("/edit/{id}")
	public String displayUpdateForm(@PathVariable Long id, Model model) {
		AutoProject autoProject = autoProjectRepo.findById(id).get();
		model.addAttribute("autoProject", autoProject);
		List<Employee> employees = employeeRepo.findAll();
		model.addAttribute("allEmployees", employees);
		
		return "autoprojects/update-autoproject";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Long id, AutoProject autoProject) {
		AutoProject autoProjectDB = autoProjectRepo.findById(id).get();

		if (autoProject.getName() != null) {
			autoProjectDB.setName(autoProject.getName());
		}

		if (autoProject.getEmployees() != null) {
			autoProjectDB.setEmployees(autoProject.getEmployees());
		}

		if (autoProject.getDescription() != null) {
			autoProjectDB.setDescription(autoProject.getDescription());
		}

		if (autoProject.getStage() != null) {
			autoProjectDB.setStage(autoProject.getStage());
		}

		autoProjectRepo.save(autoProjectDB);
		return "redirect:/autoprojects";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		autoProjectRepo.deleteById(id);
		return "redirect:/autoprojects";
	}

}
