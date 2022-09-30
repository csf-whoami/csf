/**
 * 
 */
package com.csf.data.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mba0019
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class BaseController {

	@GetMapping
	public String baseGetMethod() {
		return "test GET method";
	}
}
