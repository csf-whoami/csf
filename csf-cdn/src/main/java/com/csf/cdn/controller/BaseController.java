package com.csf.cdn.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class BaseController {

	@GetMapping
	public String baseGetMethod() {
		return "test GET method";
	}
}
