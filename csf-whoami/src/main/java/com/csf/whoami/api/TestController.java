package com.csf.whoami.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/test")
@Api
public class TestController {

	@GetMapping
	public String testMethod() {
		return "Success API";
	}
}
