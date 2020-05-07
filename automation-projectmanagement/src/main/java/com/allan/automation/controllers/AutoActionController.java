package com.allan.automation.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.allan.automation.dao.AutoActionRepository;
import com.allan.automation.entites.AutoAction;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

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
//		if (autoAction.getParameters() != null) {
//			autoActionDB.setParameters(autoAction.getParameters());
//		}
		autoActionRepo.save(autoActionDB);
		return "redirect:/autoactions";
	}

	@GetMapping("/export")
	public void export(HttpServletResponse response)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		String filename = "actions.csv";

		response.setContentType("text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

		StatefulBeanToCsv<AutoAction> writer = new StatefulBeanToCsvBuilder<AutoAction>(response.getWriter())
				.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR)
				.withOrderedResults(false).build();

		writer.write(autoActionRepo.findAll());
	}
}
