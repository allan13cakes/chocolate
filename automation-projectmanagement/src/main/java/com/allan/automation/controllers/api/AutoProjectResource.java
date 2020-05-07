package com.allan.automation.controllers.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allan.automation.dao.AutoProjectRepository;
import com.allan.automation.entites.AutoProject;
import com.allan.automation.utils.AutomationExcelGenerator;

@RestController
@RequestMapping("api/autoprojects")
public class AutoProjectResource {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AutoProjectRepository autoProjectRepo;

	@GetMapping
	public Page<AutoProject> getAutoProjects(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		Pageable pageable = PageRequest.of(page - 1, limit);
		return autoProjectRepo.findAll(pageable);
	}

	@PostMapping("/save")
	public AutoProject save(@RequestBody AutoProject autoProject) {
		logger.info("autoProject->{}", autoProject);
		return autoProjectRepo.save(autoProject);
	}

	@GetMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		autoProjectRepo.deleteById(id);
	}

	@GetMapping("/export")
	public ResponseEntity<InputStreamResource> export() throws IOException {
		List<AutoProject> autoProjects = autoProjectRepo.findAll();
		ByteArrayInputStream in = AutomationExcelGenerator.listToExcel(autoProjects);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=results.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
}
