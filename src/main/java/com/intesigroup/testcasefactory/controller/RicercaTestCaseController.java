package com.intesigroup.testcasefactory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RicercaTestCaseController {
	
	@GetMapping("/testCase/ricerca")
	public String ricercaTestCase() {
		return "ricercaTestCase";
	}
	
}
