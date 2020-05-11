package com.allan.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("compare")
public class CompareResource {
	@GetMapping
	public String test() {
		return "test";
	}
}
