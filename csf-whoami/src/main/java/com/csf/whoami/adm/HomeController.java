package com.csf.whoami.adm;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	public ModelAndView managementPage(Pageable pageable, ModelAndView model) {
		System.out.println("Go to HomeController page");
		return model;
	}
}
