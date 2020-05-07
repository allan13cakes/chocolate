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

import com.allan.automation.dao.AutoFlowRepository;
import com.allan.automation.entites.AutoFlow;

@RestController
@RequestMapping("api/autoflows")
public class AutoFlowResource {
	@Autowired
	private AutoFlowRepository autoFlowRepo;

	@GetMapping
	public Page<AutoFlow> getAutoFlows(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		Pageable pageable = PageRequest.of(page - 1, limit);
		return autoFlowRepo.findAll(pageable);
	}
	
	@PostMapping("/save")
	public AutoFlow save(AutoFlow autoFlow) {
		return autoFlowRepo.save(autoFlow);
	}
	
	@GetMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		autoFlowRepo.deleteById(id);
	}

}
