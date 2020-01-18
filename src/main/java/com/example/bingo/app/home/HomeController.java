package com.example.bingo.app.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/host/home")
public class HomeController {

	@GetMapping
	public String index() {
		
		return "/home/index";
	}
}
