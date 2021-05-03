package com.intesigroup.testcasefactory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorController {
	@GetMapping("/errorPage")
	String errorPageView(@RequestParam(name="errorStr")String errorStr, Model model){
			model.addAttribute("errorStr", errorStr);
			return "error";
	}

}
