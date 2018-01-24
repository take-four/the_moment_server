package com.takefour.themoment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by hanbyeol on 2018. 1. 3..
 */
@Controller
public class IndexController {

	@GetMapping("/")
	public String index() {
		return "index.html";
	}
}
