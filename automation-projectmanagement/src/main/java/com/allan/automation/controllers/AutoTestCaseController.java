package com.allan.automation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.allan.automation.dao.AutoTestCaseRepository;
import com.allan.automation.entites.AutoTestCase;

@Controller
@RequestMapping("autotestcases")
public class AutoTestCaseController {

	@Autowired
	private AutoTestCaseRepository autoTestCaseRepo;

	@GetMapping
	public String displayAutoTestCases(Model model) {
		List<AutoTestCase> autoTestCases = autoTestCaseRepo.findAll();
		model.addAttribute("autoTestCases", autoTestCases);
		return "autotestcases/list-autotestcase";
	}

	@GetMapping("/new")
	public String displayNewForm(Model model) {
		model.addAttribute("autoTestCase", new AutoTestCase());
		return "autotestcases/new-autotestcase";
	}

	@PostMapping("/save")
	public String save(AutoTestCase autoTestCase) {
		autoTestCaseRepo.save(autoTestCase);
		return "redirect:/autotestcases";
	}

	@GetMapping("/edit/{id}")
	public String displayEditForm(@PathVariable Long id, Model model) {
		AutoTestCase autoTestCase = autoTestCaseRepo.findById(id).get();
		model.addAttribute("autoTestCase", autoTestCase);
		return "autotestcases/update-autotestcase";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Long id, AutoTestCase autoTestCase) {
		AutoTestCase autoTestCaseDB = autoTestCaseRepo.findById(id).get();
		if (autoTestCase.getJiraKey() != null) {
			autoTestCaseDB.setJiraKey(autoTestCase.getJiraKey());
		}
		if (autoTestCase.getStage() != null) {
			autoTestCaseDB.setStage(autoTestCase.getStage());
		}
		if (autoTestCase.getSummary() != null) {
			autoTestCaseDB.setSummary(autoTestCase.getSummary());
		}

		autoTestCaseRepo.save(autoTestCaseDB);
		return "redirect:/autotestcases";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		autoTestCaseRepo.deleteById(id);
		return "redirect:/autotestcases";
	}
}
