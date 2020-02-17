package com.allan.automation.controllers;

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
import com.allan.automation.entites.AutoAction;
import com.allan.automation.entites.AutoActionFlow;
import com.allan.automation.entites.AutoFlow;
import com.allan.automation.views.AutoFlowForm;

@Controller
@RequestMapping("autoflows")
public class AutoFlowController {
	@Autowired
	private AutoFlowRepository autoFlowRepo;
	@Autowired
	private AutoActionRepository autoActionRepo;

	@GetMapping
	public String display(Model model) {
		List<AutoFlow> autoFlows = autoFlowRepo.findAll();
		model.addAttribute("autoFlows", autoFlows);
		return "autoflows/list-autoflow";
	}

	@GetMapping("/new")
	public String displayNewForm(Model model) {
		AutoFlowForm autoFlowForm = new AutoFlowForm();
		AutoFlow autoFlow = new AutoFlow();
		autoFlowForm.setAutoFlow(autoFlow);

		model.addAttribute("autoFlowForm", autoFlowForm);
		List<AutoAction> autoActions = autoActionRepo.findAll();
		model.addAttribute("autoActions", autoActions);
		return "autoflows/new-autoflow";
	}

	@PostMapping("/save")
	public String save(AutoFlowForm autoFlowForm) {
		AutoFlow autoFlow = autoFlowForm.getAutoFlow();

		List<Long> selectedAutoActionId = autoFlowForm.getSelectedAutoActionId();
		int index = 1;
		for (Long actionId : selectedAutoActionId) {
			AutoAction autoAction = autoActionRepo.findById(actionId).get();
			AutoActionFlow autoActionFlow = new AutoActionFlow();
			autoActionFlow.setActionOrder(index++);
			autoActionFlow.setAutoAction(autoAction);
			autoActionFlow.setAutoFlow(autoFlow);
			autoFlow.getAutoActionFlows().add(autoActionFlow);
		}

		autoFlowRepo.save(autoFlow);
		return "redirect:/autoflows";
	}

	@GetMapping("/edit/{id}")
	public String displayEditForm(@PathVariable Long id, Model model) {
		AutoFlow autoFlow = autoFlowRepo.findById(id).get();
		model.addAttribute("autoFlow", autoFlow);
		return "autoflows/update-autoflow";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Long id, AutoFlow autoFlow) {
		AutoFlow autoFlowDB = autoFlowRepo.findById(id).get();
		if (autoFlow.getName() != null) {
			autoFlowDB.setName(autoFlow.getName());
		}
		autoFlowRepo.save(autoFlowDB);
		return "redirect:/autoflows";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		autoFlowRepo.deleteById(id);
		return "redirect:/autoflows";
	}

}
