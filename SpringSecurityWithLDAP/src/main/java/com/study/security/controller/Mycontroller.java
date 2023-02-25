package com.study.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mycontroller {

	@GetMapping("/")
	public String home()
	{
		return "HomePage";
	}
}
