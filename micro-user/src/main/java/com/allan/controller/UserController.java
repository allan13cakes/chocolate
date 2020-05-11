package com.allan.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allan.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {
	@GetMapping("/status/check")
	public String status() {
		return "working";
	}
	
	@GetMapping
	public String display(@RequestParam(value = "page", defaultValue = "10") int page,
			@RequestParam(value = "limit", defaultValue = "1") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		return "this is display method, page=" + page + ",limit=" + limit + ",sort=" + sort;
	}

	@GetMapping(path = "/{id}", produces =

	{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })

	public UserRest get(@PathVariable long id) {
		UserRest user = new UserRest();
		user.setEmail("myemail");
		user.setName("name");
		user.setId(id);
		return user;
	}

	@PostMapping
	public String create() {
		return "this is create method";
	}

	@PutMapping
	public String update() {
		return "this is update method";
	}

	@DeleteMapping
	public String delete() {
		return "this is delete method";
	}
}
