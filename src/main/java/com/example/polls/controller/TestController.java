package com.example.polls.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
