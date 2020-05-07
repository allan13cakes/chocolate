package com.allan.automation.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allan.automation.dao.AutoTestCaseRepository;
import com.allan.automation.entites.AutoTestCase;

@RestController
@RequestMapping("api/autotestcases")
public class AutoTestCaseResource {

	@Autowired
	private AutoTestCaseRepository autoTestCaseRepo;

	@GetMapping
	public Page<AutoTestCase> getAutoTestCases(
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
		Pageable query = PageRequest.of(page - 1, limit);
		return  autoTestCaseRepo.findAll(query);
	}

	@PostMapping("/save")
	public AutoTestCase save(AutoTestCase autoTestCase) {
		return autoTestCaseRepo.save(autoTestCase);
	}

	@GetMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		autoTestCaseRepo.deleteById(id);
	}
}
