package com.example.polls.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.polls.model.audit.UserDateAudit;

@RestController
public class TestController {
	
	@GetMapping("/testGet")
	public String testGet() {
		return "Hello World";
	}
	
	@PostMapping("/testPost")
	public String testPost() {
		return "Hello World";
	}
}
