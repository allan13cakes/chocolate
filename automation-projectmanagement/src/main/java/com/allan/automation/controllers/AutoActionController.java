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
import com.allan.automation.entites.AutoAction;

@Controller
@RequestMapping("autoactions")
public class AutoActionController {
	@Autowired
	private AutoActionRepository autoActionRepo;

	@GetMapping
	public String displayAutoActions(Model model) {
		List<AutoAction> autoActions = autoActionRepo.findAll();
		model.addAttribute("autoActions", autoActions);
		return "autoactions/list-autoaction";
	}

	@GetMapping("/new")
	public String displayNewForm(Model model) {
		AutoAction autoAction = new AutoAction();
		model.addAttribute("autoAction", autoAction);
		return "autoactions/new-autoaction";
	}

	@PostMapping("/save")
	public String save(AutoAction autoAction) {
		autoActionRepo.save(autoAction);
		return "redirect:/autoactions";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		autoActionRepo.deleteById(id);
		return "redirect:/autoactions";
	}

	@GetMapping("/edit/{id}")
	public String displayUpdateForm(@PathVariable Long id, Model model) {
		AutoAction autoAction = autoActionRepo.findById(id).get();
		model.addAttribute("autoAction", autoAction);
		return "autoactions/update-autoaction";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Long id, AutoAction autoAction) {
		AutoAction autoActionDB = autoActionRepo.findById(id).get();
		if (autoAction.getName() != null) {
			autoActionDB.setName(autoAction.getName());
		}
		if (autoAction.getDescription() != null) {
			autoActionDB.setDescription(autoAction.getDescription());
		}
		if (autoAction.getParameters() != null) {
			autoActionDB.setParameters(autoAction.getParameters());
		}
		autoActionRepo.save(autoActionDB);
		return "redirect:/autoactions";
	}
}
