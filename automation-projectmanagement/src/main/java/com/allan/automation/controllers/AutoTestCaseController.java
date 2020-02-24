package com.allan.automation.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.allan.automation.dao.AutoActionRepository;
import com.allan.automation.dao.AutoFlowRepository;
import com.allan.automation.dao.AutoTestCaseRepository;
import com.allan.automation.entites.AutoAction;
import com.allan.automation.entites.AutoFlow;
import com.allan.automation.entites.AutoTestCase;
import com.allan.automation.entites.AutoTestStep;
import com.allan.automation.views.AutoTestCaseForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@RequestMapping("autotestcases")
public class AutoTestCaseController {

	@Autowired
	private AutoTestCaseRepository autoTestCaseRepo;

	@Autowired
	private AutoActionRepository autoActionRepo;

	@Autowired
	private AutoFlowRepository autoFlowRepo;

	@GetMapping
	public String displayAutoTestCases(Model model) {
		List<AutoTestCase> autoTestCases = autoTestCaseRepo.findAll();
		model.addAttribute("autoTestCases", autoTestCases);
		return "autotestcases/list-autotestcase";
	}

	@GetMapping("/new")
	public String displayNewForm(Model model) {

		AutoTestCase autoTestCase = new AutoTestCase();
		AutoTestCaseForm autoTestCaseForm = new AutoTestCaseForm();
		autoTestCaseForm.setAutoTestCase(autoTestCase);

		model.addAttribute("autoTestCaseForm", autoTestCaseForm);

		List<AutoAction> autoActions = autoActionRepo.findAll();
		model.addAttribute("autoActions", autoActions);

		List<AutoFlow> autoFlows = autoFlowRepo.findAll();
		model.addAttribute("autoFlows", autoFlows);
		return "autotestcases/new-autotestcase";
	}

	@PostMapping("/save")
	public String save(AutoTestCaseForm autoTestCaseForm)
			throws UnsupportedEncodingException, JsonMappingException, JsonProcessingException {
		AutoTestCase autoTestCase = autoTestCaseForm.getAutoTestCase();
		List<String> selectedActionIds = autoTestCaseForm.getSelectedActionIds();
		int index = 1;
		for (String a : selectedActionIds) {
			a = URLDecoder.decode(a, StandardCharsets.UTF_8.toString());
			String type = a.split("-")[1];
			String id = a.split("-")[2];
			String parameterStr = a.split("-")[3];
			String description = a.split("-")[4];
			// ObjectMapper mapper = new ObjectMapper();
			// List<String> parameterArr = mapper.readValue(parameterStr, new
			// TypeReference<List<String>>() {
			// });

			AutoTestStep autoTestStep = new AutoTestStep();

			if ("0".equals(type)) {
				// action
				AutoAction autoAction = autoActionRepo.findById(new Long(id)).get();
				autoTestStep.setAutoAction(autoAction);
			} else {
				// flow
				AutoFlow autoFlow = autoFlowRepo.findById(new Long(id)).get();
				autoTestStep.setAutoFlow(autoFlow);
			}

			autoTestStep.setStepOrder(index++);
			autoTestStep.setDescription(description);
			autoTestStep.setTestDatas(parameterStr);
			autoTestStep.setAutoTestCase(autoTestCase);
			autoTestCase.getAutoTestSteps().add(autoTestStep);
		}
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
