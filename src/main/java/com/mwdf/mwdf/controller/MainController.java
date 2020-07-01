package com.mwdf.mwdf.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class MainController {

	/*@RequestMapping("/")
	@ResponseBody
	public String sayHello() {
		return "Hello World!!!";
	}*/
	
	@GetMapping("/")
	public String accueilPage()
	{
		return "index"; //le nom du template
	}
}
